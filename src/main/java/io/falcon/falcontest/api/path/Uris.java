package io.falcon.falcontest.api.path;

/**
 * The class is used to model the tree structure of the endpoints of the service.
 * Usage is Uris.LEVEL1.LEVEL2.LEVELX.ENDPOINT.URI. The URI constant's visibility is private,
 * unless the endpoint should be exposed.
 *
 */
public final class Uris {

  private Uris() {

  }

  public static class API {
    private static final String URI = "/api";

    public static final class V1 {
      private static final String URI = API.URI + "/v1";

      public static final class SERVICES {
        private static final String URI = V1.URI + "/services";

        public static final class TUMBLR {
          public static final String URI = SERVICES.URI + "/tumblr";
        }
      }
    }
  }
}
