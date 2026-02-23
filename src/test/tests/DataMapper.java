public class DataMapper {


/*
json data

[
  { "id": 1 },
  { "id": 2 },
  { "id": 3 }
]

Util/jsondatareder.java
public static List<Map<String, Object>> readJson(String path) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(
                new File(path),
                new TypeReference<List<Map<String, Object>>>() {}
        );
    }



 */

    @DataProvider(name = "postIds")
    public Object[][] postsData() throws IOException {
        List<Map<String,Object>> data = JsonDataReader.readJson("src/test/resources/testdata/posts.json");

        Object[][] array = new Object[data.size()][1];

        for (int i = 0; i < data.size(); i++) {
            array[i][0] = data.get(i).get("id");
        }

        return array;

    }

    @Test(dataProvider = "postIds")
    public void validatePosts(int id) {
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/" + id)
                .then()
                .statusCode(200);
    }
}