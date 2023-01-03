package utils;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import com.fabricktest.config.ApiConnectionConfig;

/*
 * 
 * @author Davide Trenti
 */

@Configuration
public class Utils {

	public static String createUri(String path, String connectionType, String host, String accountId,
			MultiValueMap<String, String> params) {

		return UriComponentsBuilder.newInstance().scheme(connectionType).host(host).path(path).queryParams(params)
				.build().expand(accountId).encode().toUriString();
	}

	public static HttpHeaders createHeaders(ApiConnectionConfig apiConnectionConfiguration) {
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		header.set("Api-Key", apiConnectionConfiguration.getKeyValue());
		header.set("Auth-Schema", apiConnectionConfiguration.getAuthSchemaValue());
		header.set("X-Time-Zone", "Europe/Rome");
		return header;
	}

}