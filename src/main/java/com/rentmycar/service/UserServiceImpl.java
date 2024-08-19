package com.rentmycar.service;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.rentmycar.custom_exception.ConflictException;
import com.rentmycar.custom_exception.CustomAuthenticationException;
import com.rentmycar.custom_exception.CustomBadRequestException;
import com.rentmycar.custom_exception.ResourceNotFoundException;
import com.rentmycar.dao.DrivingLicenseDao;
import com.rentmycar.dao.UserDao;
import com.rentmycar.dto.ApiResponseDto;
import com.rentmycar.dto.DrivingLicenseDto;
import com.rentmycar.dto.RegisterUserReqDto;
import com.rentmycar.dto.RegisterUserResDto;
import com.rentmycar.dto.RegisterUserWithDlReqDto;
import com.rentmycar.dto.RegisterUserWithDlResDto;
import com.rentmycar.dto.SignInRequestDto;
import com.rentmycar.dto.SignInResponseDto;
import com.rentmycar.dto.UpdateBasicUserDetailsDto;
import com.rentmycar.dto.UserDetailsResponseDto;
import com.rentmycar.entity.DrivingLicense;
import com.rentmycar.entity.User;
import com.rentmycar.entity.UserRoleEnum;
import com.rentmycar.security.JwtUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private DrivingLicenseDao drivingLicenseDao;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private AuthenticationManager authMgr;

	// Method for SignIn on basis of role
	@Override
	public Optional<SignInResponseDto> authenticateUser(SignInRequestDto request) {
		System.out.println("in Log in " + request);
		// create a token(implementation of Authentication i/f)
		// to store un verified user email n pwd
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());
		// invoke auth mgr's authenticate method;
		Authentication verifiedToken = authMgr.authenticate(token);
		// => authentication n authorization successful !
		System.out.println(verifiedToken.getPrincipal().getClass());// custom user details object
		// create JWT n send it to the clnt in response
		SignInResponseDto resp = new SignInResponseDto(jwtUtils.generateJwtToken(verifiedToken), "Successful Auth!!!!");
		return Optional.of(resp);
		// User userEntity = userDao
//				.findByEmailAndPasswordAndRoleEnum(signInRequestDto.getEmail(), signInRequestDto.getPassword(),
//						signInRequestDto.getRoleEnum())
//				.orElseThrow(() -> new CustomAuthenticationException("Invalid Email or Password !"));
//		if (userEntity.getIsDeleted()) {
//			throw new CustomAuthorizationException("User is Deactivated!");
//		} else
//			// valid login
//			return Optional.of(mapper.map(userEntity, SignInResponseDto.class));
	}

	// Register User with basic details
	@Override
	public Optional<RegisterUserResDto> registerUser(@RequestBody RegisterUserReqDto registerUserReqDto) {
		if (!registerUserReqDto.getPassword().equals(registerUserReqDto.getConfirmPassword())) {
			throw new ConstraintViolationException("password mismatch", null);
		} else if (!(registerUserReqDto.getRoleEnum().equals(UserRoleEnum.ROLE_ADMIN)
				|| registerUserReqDto.getRoleEnum().equals(UserRoleEnum.ROLE_GUEST)
				|| registerUserReqDto.getRoleEnum().equals(UserRoleEnum.ROLE_HOST))) {
			throw new ConstraintViolationException("invalid user role, user HOST, GUEST, ADMIN only", null);
		} else if (userDao.existsByMobileAndEmailAndRoleEnum(registerUserReqDto.getMobile(),
				registerUserReqDto.getEmail(), registerUserReqDto.getRoleEnum())) {
			throw new ConflictException("user with given email and mobile already registered");
		} else if (userDao.existsByEmailAndRoleEnum(registerUserReqDto.getEmail(), registerUserReqDto.getRoleEnum())) {
			throw new ConflictException("user with given email already registered");
		} else if (userDao.existsByMobileAndRoleEnum(registerUserReqDto.getMobile(),
				registerUserReqDto.getRoleEnum())) {
			throw new ConflictException("user with given mobile already registered");
		} else {

			User tUser = mapper.map(registerUserReqDto, User.class);

			tUser.setPassword(passwordEncoder.encode(registerUserReqDto.getPassword()));
			tUser.setIsDeleted(false);
			User pUser = userDao.save(tUser);
			return Optional.of(mapper.map(pUser, RegisterUserResDto.class));

		}

	}

	// Register User with driving license
	@Override
	public Optional<RegisterUserWithDlResDto> registerUser(@RequestBody RegisterUserWithDlReqDto registerUserWithDlReqDto) {
		if (!registerUserWithDlReqDto.getPassword().equals(registerUserWithDlReqDto.getConfirmPassword())) {
			throw new ConstraintViolationException("password mismatch", null);
		} else if (!(registerUserWithDlReqDto.getRoleEnum().equals(UserRoleEnum.ROLE_ADMIN)
				|| registerUserWithDlReqDto.getRoleEnum().equals(UserRoleEnum.ROLE_GUEST)
				|| registerUserWithDlReqDto.getRoleEnum().equals(UserRoleEnum.ROLE_HOST))) {
			throw new ConstraintViolationException("invalid user role, use HOST, GUEST, ADMIN only", null);
		} else if (registerUserWithDlReqDto.getDrivingLicenseDto().getIssueDate()
				.isAfter(registerUserWithDlReqDto.getDrivingLicenseDto().getExpirationDate())) {
			throw new ConstraintViolationException("expiry should come after issue date of dl", null);
		} else if (userDao.existsByMobileAndEmailAndRoleEnum(registerUserWithDlReqDto.getMobile(),
				registerUserWithDlReqDto.getEmail(), registerUserWithDlReqDto.getRoleEnum())) {
			throw new ConflictException("user with given email and mobile already registered");
		} else if (userDao.existsByEmailAndRoleEnum(registerUserWithDlReqDto.getEmail(),
				registerUserWithDlReqDto.getRoleEnum())) {
			throw new ConflictException("user with given email already registered");
		} else if (userDao.existsByMobileAndRoleEnum(registerUserWithDlReqDto.getMobile(),
				registerUserWithDlReqDto.getRoleEnum())) {
			throw new ConflictException("user with given mobile already registered");
		} else {

			User tUser = mapper.map(registerUserWithDlReqDto, User.class);
			tUser.setIsDeleted(false);
			DrivingLicenseDto drivingLicenseDto = registerUserWithDlReqDto.getDrivingLicenseDto();
			if (!drivingLicenseDao.existsByDrivingLicenseNo(drivingLicenseDto.getDrivingLicenseNo())) {
				DrivingLicense tDrivingLicense = mapper.map(drivingLicenseDto, DrivingLicense.class);
				User pUser = userDao.save(tUser);
				pUser.addDrivingLicense(tDrivingLicense);
				RegisterUserWithDlResDto registerUserWithDlResDto = mapper.map(pUser, RegisterUserWithDlResDto.class);
				DrivingLicense pDrivingLicense = drivingLicenseDao.save(tDrivingLicense);
				DrivingLicenseDto drivingLicenseDto2 = mapper.map(pDrivingLicense, DrivingLicenseDto.class);
				registerUserWithDlResDto.setDrivingLicenseDto(drivingLicenseDto2);

				return Optional.of(registerUserWithDlResDto);

			}
			throw new ConflictException("dl exists , please enter your dl");
		}

	}

	// method to Show User Profile details by Id
	@Override
	public Optional<UserDetailsResponseDto> getUserProfileDetails(Long userId) {
		User userEntity = userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid User Id !"));

		if (userEntity.getIsDeleted())
			throw new CustomAuthenticationException("User is De-Activated !");

		UserDetailsResponseDto userDetailsResponseDto = mapper.map(userEntity, UserDetailsResponseDto.class);
		if (userEntity.getDrivingLicense() != null) {
			DrivingLicenseDto drivingLicenseDto = mapper.map(userEntity.getDrivingLicense(), DrivingLicenseDto.class);
			userDetailsResponseDto.setDrivingLicenseDto(drivingLicenseDto);
		}

		return Optional.of(userDetailsResponseDto);

	}

	// update user basic details
	@Override
	public Optional<UpdateBasicUserDetailsDto> updateBasicUserDetails(Long userId,
			UpdateBasicUserDetailsDto updatedUserDetails) {
		if (userDao.existsById(userId)) {
			User userEntity = userDao.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("Invalid User Id !"));
			userEntity.setFirstName(updatedUserDetails.getFirstName());
			userEntity.setLastName(updatedUserDetails.getLastName());
			userEntity.setEmail(updatedUserDetails.getEmail());
			userEntity.setMobile(updatedUserDetails.getMobile());
			userEntity.setPassword(updatedUserDetails.getPassword());
			UpdateBasicUserDetailsDto updatedUserDto = mapper.map(userEntity, UpdateBasicUserDetailsDto.class);

			return Optional.of(updatedUserDto);
		}
		throw new ResourceNotFoundException("Invalid User ID !");
	}

	// soft-delete user with userId
	@Override
	public ApiResponseDto softDeleteUserById(Long userId) {
		User puser = userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Invalid User Id."));

		// Check if the user is an admin
		if ("ADMIN" == puser.getRoleEnum().name())
			throw new CustomBadRequestException("Admin users cannot be deleted.");

		if (puser.getIsDeleted())
			throw new ResourceNotFoundException("User is already deleted.");

		puser.setIsDeleted(true);
		System.out.println("user delete status made true");
		return new ApiResponseDto("User Deleted Successfully!");
	}

	// activate the user with email and password
	@Override
	public ApiResponseDto activateUser(SignInRequestDto activationreqDto) {
		User pUser = userDao
				.findByEmailAndPasswordAndRoleEnum(activationreqDto.getEmail(), activationreqDto.getPassword(),
						activationreqDto.getRoleEnum())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Email or Password !"));
		if (!pUser.getIsDeleted()) {
			throw new ConflictException("already an active user");
		}
		pUser.setIsDeleted(false);
		userDao.save(pUser);
		return new ApiResponseDto("activation successfull");

	}

}
