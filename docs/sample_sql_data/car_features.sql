-- Example 1: All features set to false
INSERT INTO car_features (has_usb_charger, has_bluetooth, has_power_steering, has_air_bags, has_abs, has_ac)
VALUES (false, false, false, false, false, false);

-- Example 2: All features set to true
INSERT INTO car_features (has_usb_charger, has_bluetooth, has_power_steering, has_air_bags, has_abs, has_ac)
VALUES (true, true, true, true, true, true);

-- Example 3: USB Charger and Bluetooth only
INSERT INTO car_features (has_usb_charger, has_bluetooth, has_power_steering, has_air_bags, has_abs, has_ac)
VALUES (true, true, false, false, false, false);

-- Example 4: Power Steering and Air Bags only
INSERT INTO car_features (has_usb_charger, has_bluetooth, has_power_steering, has_air_bags, has_abs, has_ac)
VALUES (false, false, true, true, false, false);

-- Example 5: ABS and AC only
INSERT INTO car_features (has_usb_charger, has_bluetooth, has_power_steering, has_air_bags, has_abs, has_ac)
VALUES (false, false, false, false, true, true);

-- Example 6: USB Charger, Bluetooth, and AC
INSERT INTO car_features (has_usb_charger, has_bluetooth, has_power_steering, has_air_bags, has_abs, has_ac)
VALUES (true, true, false, false, false, true);

-- Example 7: Power Steering, ABS, and Air Bags
INSERT INTO car_features (has_usb_charger, has_bluetooth, has_power_steering, has_air_bags, has_abs, has_ac)
VALUES (false, false, true, true, true, false);

-- Example 8: Bluetooth and ABS only
INSERT INTO car_features (has_usb_charger, has_bluetooth, has_power_steering, has_air_bags, has_abs, has_ac)
VALUES (false, true, false, false, true, false);

-- Example 9: AC and Power Steering only
INSERT INTO car_features (has_usb_charger, has_bluetooth, has_power_steering, has_air_bags, has_abs, has_ac)
VALUES (false, false, true, false, false, true);

-- Example 10: USB Charger, Bluetooth, and Power Steering
INSERT INTO car_features (has_usb_charger, has_bluetooth, has_power_steering, has_air_bags, has_abs, has_ac)
VALUES (true, true, true, false, false, false);
