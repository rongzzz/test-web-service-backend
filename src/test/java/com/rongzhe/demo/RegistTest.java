package com.rongzhe.demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.jupiter.api.Test;

public class RegistTest {

	@Test
	void testRegist() {
		for (int i = 5; i <= 10; i++) {
			// callRegistApi(String.format("%02d", i));
		}

	}

	private void callRegistApi(String index) {
		final String jsonInput = "{\"account\":\"testregist%s\",\"password\":\"testregist%s\",\"userDetail\":{\"firstName\":\"%s\",\"lastName\":\"testregist\",\"displayName\":\"dtestregist%s\"},\"userEmails\":[{\"email\":\"test%s@aaa.com\"},{\"email\":\"test%s@bbb.com\"}]}";

		try {
			final StringBuffer buffer = new StringBuffer();
			final URL url = new URL("http://localhost:8080/regist");
			final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");

			connection.setDoOutput(true);
			final OutputStream os = connection.getOutputStream();
			final byte[] input = jsonInput.replaceAll("%s", index).getBytes("utf-8");
			os.write(input, 0, input.length);

			connection.connect();
			final InputStream inputStream = connection.getInputStream();
			final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			System.out.println(buffer.toString());
			// close
			connection.disconnect();
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
