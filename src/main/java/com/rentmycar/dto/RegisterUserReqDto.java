package com.rentmycar.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.rentmycar.entity.UserRoleEnum;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserReqDto {

	private Long id;

	@NotBlank(message = "firstName must not be blannk")
	String firstName; // VARCHAR(20),NOT NULL

	@NotBlank(message = "last name must have a value")
	String lastName; // VARCHAR(20),NOT NULL

	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number format. It should be exactly 10 digits long and start with 6, 7, 8, or 9.")
	String mobile; // VARCHAR(12),UNIQUE NOT NULL
	/*
	 * ^[6-9]: The string must start with a digit between 6 and 9. \d{9}$: After the
	 * first digit, there must be exactly 9 more digits, making the total length 10
	 * digits. ^ and $ together ensure that the entire string matches the pattern,
	 * with no extra characters before or after.
	 */

	@NotBlank(message = "invalid email id , should not be blank")
	@Email
	String email; // VARCHAR(25),UNIQUE NOT NULL

	@NotBlank
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^\\w\\s]).{8,20})", message = "Blank or Invalid password format , "
			+ "it must contain at least one digit, at least one lower case letter, "
			+ "at least one specilal char(@,$,#,*) and should be 5 to 20 char long!!!!")
	String password; // VARCHAR(70), NOT NULL
	/*
	 * (?=.*\d) ensures at least one digit. (?=.*[a-z]) ensures at least one
	 * lowercase letter. (?=.*[A-Z]) ensures at least one uppercase letter.
	 * (?=.*[^\w\s]) ensures at least one special character. This part includes any
	 * character that is not a word character (\w matches letters and digits) or
	 * whitespace (\s matches spaces, tabs, etc.). .{5,20} ensures the password
	 * length is between 5 and 20 characters. With this regex, any special character
	 * (like !, @, #, $, %, etc.) will be acceptable.
	 */

	@NotBlank
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Blank or Invalid password!!!!")
	String confirmPassword; // VARCHAR(70), NOT NULL
	/*
	 * This regular expression ensures that a string: - Contains at least one digit.
	 * - Contains at least one lowercase letter. - Contains at least one special
	 * character from the set `#`, `@`, `$`, or `*`. - Is between 5 and 20 -
	 * characters long.
	 */

	@NotNull
	private UserRoleEnum roleEnum;

}

/*
 * PASSWORD REGEX ----> This regular expression
 * `((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})` is used to validate a string that
 * meets specific criteria for password complexity. Let's break it down step by
 * step:
 * 
 * 1. **`(?=.*\\d)`**: - `(?=...)`: This is a positive lookahead assertion. It
 * checks for the presence of a pattern without consuming characters (i.e., it
 * doesn't move the cursor forward). - `\\d`: This matches any digit (0-9). The
 * double backslash (`\\`) is used to escape the backslash in languages like
 * JavaScript or Java. - **Meaning**: Ensure the string contains at least one
 * digit.
 * 
 * 2. **`(?=.*[a-z])`**: - `(?=...)`: Another positive lookahead assertion. -
 * `[a-z]`: This matches any lowercase letter (a-z). - **Meaning**: Ensure the
 * string contains at least one lowercase letter.
 * 
 * 3. **`(?=.*[#@$*])`**: - `(?=...)`: Another positive lookahead assertion. -
 * `[#@$*]`: This matches any of the specified special characters `#`, `@`, `$`,
 * or `*`. - **Meaning**: Ensure the string contains at least one of these
 * special characters.
 * 
 * 4. **`.{5,20}`**: - `.`: This matches any character except a newline. -
 * `{5,20}`: This quantifier specifies that the preceding character (or group)
 * must appear at least 5 times and at most 20 times. - **Meaning**: The string
 * must be between 5 and 20 characters long.
 * 
 * ### Summary of the Regex
 * 
 * This regular expression ensures that a string: - Contains at least one digit.
 * - Contains at least one lowercase letter. - Contains at least one special
 * character from the set `#`, `@`, `$`, or `*`. - Is between 5 and 20
 * characters long.
 */

/*
 * MOBILE REGEX The regular expression `^[6-9]\d{9}$` is used to validate Indian
 * mobile phone numbers. Here's a detailed breakdown of what each part of the
 * regex means:
 * 
 * ### Components of the Regex
 * 
 * 1. **`^`**: Asserts the start of the string. This means the match must begin
 * at the beginning of the string.
 * 
 * 2. **`[6-9]`**: A character class that matches any single digit from `6` to
 * `9`. This indicates that the first digit of the mobile number must be between
 * `6` and `9`.
 * 
 * 3. **`\d`**: Matches any digit (equivalent to `[0-9]`).
 * 
 * 4. **`\d{9}`**: Matches exactly 9 digits. The `{9}` quantifier specifies that
 * the preceding digit pattern (`\d`) must appear exactly 9 times.
 * 
 * 5. **`$`**: Asserts the end of the string. This means the match must end at
 * the end of the string.
 * 
 * ### Combined Explanation
 * 
 * - **`^[6-9]`**: The string must start with a digit between `6` and `9`. -
 * **`\d{9}$`**: After the first digit, there must be exactly 9 more digits,
 * making the total length 10 digits. - **`^` and `$`** together ensure that the
 * entire string matches the pattern, with no extra characters before or after.
 * 
 * ### Example Matches
 * 
 * - Valid: `9876543210`, `6789012345` - Invalid: `1234567890` (starts with
 * `1`), `567890123` (only 9 digits), `09876543210` (starts with `0`)
 * 
 * ### Usage
 * 
 * This regex is commonly used in forms or applications where users need to
 * input a valid Indian mobile number.
 */