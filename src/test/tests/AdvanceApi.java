import packagename.base.BaseTest

import static org.hamcrest.Matchers.*;


public class AdvanceApi extends BaseTest {


    @Test(
            priority = 1,
            groups = {"smoke", "get"}
    )
    public void getPostById(){
        RestAssured
                .given()
                .spec(RequestSpecFactory.getRequestSpec())
                .pathParam("id", 1)
                .when()
                .get(ApiConstants.POSTS_ENDPOINT + "/{id}")
                .then()
                .spec(ResponseSpecFactory.get200ResponseSpec())
                .body("id", equalTo(1))
                .body("userId", equalTo(1))
                .body("title", notNullValue())
                .body("body", notNullValue())
                .log().body();
    }


    public void getCommentsByPostId(){
        RestAssured
                .given()
                .spec(RequestSpecFactory.getRequestSpec())
                .queryParam("postId", 1)
                .when()
                .get(ApiConstants.COMMENTS_ENDPOINT)
                .then()
                .spec(ResponseSpecFactory.get200ResponseSpec())
                .body("size()", greaterThan(0))
                .body("[0].postId", equalTo(ApiConstants.VALID_POST_ID))
                .body("[0].email", containsString("@"))
                .log().body();
//        System.out.println("Number of posts: " + response.jsonPath().getList("$").size());
    }


    @Test
    public void serializePostTest() {

        PostRequest request =
                new PostRequest("happiness", "its a happy ending always", 1);

        given()
                .spec(RequestSpecFactory.getRequestSpec())
                .body(request)   // Serialization happens here
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("id", notNullValue());
    }

    @Test
    public void deserializePostTest() {

        PostResponse response =
                given()
                        .spec(RequestSpecFactory.getRequestSpec())
//                       .log().all()
                        .when()
                        .get("/posts/1")
                        .then()
                        .statusCode(200)
                        .extract()

                        // VAriations -----------------------------


//                        .log().all()
//                        .body(matchesJsonSchemaInClasspath("schema/post-schema.json"));
//                        .body(
//                                "id", equalTo(1),
//                                "title", notNullValue(),
//                                "body", notNullValue(),
//                                "userId", equalTo(1)
//                        )
//                        .log().ifError();
//                        .time(lessThan(2000L));

                        .as(PostResponse.class);

        Assert.assertEquals(response.id(), 1);
        Assert.assertEquals(response.userId(), 1);
        Assert.assertNotNull(response.title());
    }

    @Test
    public void deserializeNestedUserTest() {
        UserResponse user =
                given()
                        .spec(RequestSpecFactory.getRequestSpec())
                        .when()
                        .get("/users/1")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(UserResponse.class);

        Assert.assertEquals(user.id(), 1);
        Assert.assertEquals(user.address().city(), "Gwenborough");
        Assert.assertNotNull(user.address().geo().getLat());
    }

    @Test
    public void deserializeListTest() {
        List<PostResponse> posts =
                given()
                        .spec(RequestSpecFactory.getRequestSpec())
                        .when()
                        .get("/posts?userId=1")
                        .then()
                        .statusCode(200)
                        .extract()
                        .jsonPath()
                        .getList("", PostResponse.class);

        Assert.assertFalse(posts.isEmpty());
        Assert.assertTrue(posts.stream().allMatch(p -> p.userId() == 1));
    }

    @Test
    public void serializeAndDeserializeTest() {

        PostRequest request =
                new PostRequest("Automation", "RestAssured is powerful", 1);

        PostResponse response =
                given()
                        .spec(RequestSpecFactory.postRequestSpec())
                        .body(request)
                        .when()
                        .post("/posts")
                        .then()
                        .statusCode(201)
                        .extract()
                        .as(PostResponse.class);

        Assert.assertEquals(response.title(), "Automation");
        Assert.assertEquals(response.userId(), 1);
    }


    @Test
    public void validateHeaders() {

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .header("Correlation-Id", UUID.randomUUID().toString())
                        .when()
                        .get("https://jsonplaceholder.typicode.com/posts/1");
        response.then().statusCode(200);

        // Validate Response Header
        response.then().header("Content-Type", containsString("application/json"));

        // Extract Header
        String server = response.getHeader("Server");
        Sy



}