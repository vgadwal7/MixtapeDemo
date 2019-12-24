package com.highspot.mixtape;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.common.base.Strings;
import com.highspot.mixtape.service.MixtapeDemoConstants;
import com.highspot.mixtape.service.MixtapeService;

/**
 * Mixtape demo application start file
 * 
 * @author VG
 *
 */
@SpringBootApplication
public class MixtapeDemoApplication {
	public static void main(String[] args) {

		SpringApplication.run(MixtapeDemoApplication.class, args);
		try {
			// check number of inputs and input form
			if (checkInputs(args)) {
				MixtapeService.processChanges(args[0], args[1], args[2]);
			} else {
				throw new Exception(MixtapeDemoConstants.INVALID_INPUTS);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * check if the number of args is 3 and the each String in the args is neither
	 * null or empty and file format is .json
	 * 
	 * @param args
	 * @return
	 */
	private static boolean checkInputs(String[] args) {
		return args.length == 3 && Arrays.stream(args).parallel().filter(
				inputStr -> !Strings.isNullOrEmpty(inputStr) && (inputStr.endsWith(MixtapeDemoConstants.FILE_FORMAT)))
				.collect(Collectors.toList()).size() == 3;
	}

}
