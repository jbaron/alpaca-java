package io.github.mainstringargs.alpaca.rest;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The Class AlpacaUrlBuilder.
 */
public abstract class AlpacaUrlBuilder {


  /** The Constant VERSION. */
  private final static String VERSION = "v1";

  /** The Constant URL_SEPARATOR. */
  public final static String URL_SEPARATOR = "/";

  /** The parameters. */
  public final Map<String, String> parameters = new LinkedHashMap<String, String>();

  /** The base url. */
  private String baseUrl;


  /**
   * Instantiates a new alpaca url builder.
   *
   * @param baseUrl the base url
   */
  public AlpacaUrlBuilder(String baseUrl) {
    this.baseUrl = baseUrl;

  }

  /**
   * Append URL parameters.
   *
   * @param parameterKey the parameter key
   * @param parameterValue the parameter value
   */
  public void appendURLParameters(String parameterKey, String parameterValue) {
    parameters.put(parameterKey, parameterValue);
  }


  /**
   * Gets the endpoint.
   *
   * @return the endpoint
   */
  public abstract String getEndpoint();

  /**
   * Gets the url.
   *
   * @return the url
   */
  public String getURL() {

    StringBuilder builder = new StringBuilder(baseUrl);
    builder.append(URL_SEPARATOR);
    builder.append(VERSION);
    builder.append(URL_SEPARATOR);
    builder.append(getEndpoint());

    if (!parameters.isEmpty()) {
      builder.append('?');

      for (Entry<String, String> entry : parameters.entrySet()) {
        builder.append(entry.getKey().trim());
        builder.append('=');
        builder.append(entry.getValue().trim());
        builder.append('&');
      }

      // removes last &
      builder.deleteCharAt(builder.length() - 1);
    }

    return builder.toString();
  }



}