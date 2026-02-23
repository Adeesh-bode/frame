public class Auth {

    @Test
    public void validateBasicAuthSuccess() {
        /*
        Without Preemptive (Normal Basic Auth Flow)

        Client sends request ❌ (no credentials)

        Server responds → 401 Unauthorized

        Server says: “I need Basic authentication”

        Client sends request again ✅ (with credentials
         */

        // preemtive - optional here
        // The client sends the username and password immediately with the first request,
        // without waiting for the server to ask for authentication.

        given()
                .auth().preemptive().basic("user", "passwd") // UTH:
                .when()
                .get("https://httpbin.org/basic-auth/user/passwd")
                .then()
                .statusCode(200);
    }



//    BEARER TOKKENNNNN

    private final String TOKEN = "wrong token here";

    @Test
    public void verifyAccessWithValidToken() {
        given()
                .header("Authorization", "Bearer " + TOKEN)
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200);
    };

    @Test
    public void verifyAccessWithInvalidToken() {
        given()
                .header("Authorization", "Bearer invalid_token")
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(401);
    };



    // OAUTH
    private final String TOKEN = "wrong token here";

    @Test
    public void validateOAuthSuccess(){
        Response res = given()
                .auth().oauth2(TOKEN)
                .when()
                .get("https://api.github.com/user/repo")
                .then()
                .statusCode(200)
//                .log().ifError()
                .extract().response();


        System.out.println(res.getBody().prettyPrint());
    }





    // COOKIES
    @Test
    public void validateCookieSuccess(){
        given()
                .cookie("sessionId", "abc123")
                .when()
                .get("https://httpbin.org/cookies")
                .then()
                .statusCode(200);
    }

    // header x-api-key
    @Test
    void testInvalidEndpoint() {

        given()
                .header("x-api-key", API_KEY)
                .log().all()
                .when()
                .get("/api/unknown/999")
                .then()
                .log().all()
                .statusCode(404);
    }



}