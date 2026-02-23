public class TestngBasics {

    Calculator calculator;

    @BeforeClass
    // @Test(priority = -1)
    public void testInitializeCalculator(){
        calculator = new Calculator();
        Assert.assertNotNull(calculator, "Calculator should not be null");
        System.out.println("Initialize calculator Resources");
    }

    @Test( dependsOnMethods = "testInitializeCalculator")
    public void testSubtract(){
        Assert.assertEquals(calculator.subtract(2, 3), -1, "Subtract result should be -1");
        System.out.println( "Subtraction test passed");
    }

    @Test
    @Parameters({ "add.a", "add.b", "add.result" })
    public void testAddition(int a, int b, int expectedResult) {
        Assert.assertEquals(
                calculator.add(a, b),
                expectedResult,
                "Addition result is incorrect"
        );
    }


    // Soft assert
    @Test
    public void testMultipleOperationsWithSoftAssert() {
        SoftAssert softAssert = new SoftAssert();

        // Addition
        softAssert.assertEquals(
                calculator.add(2, 3),
                5,
                "Addition failed"
        );

        // Division
        softAssert.assertEquals(
                calculator.divide(10, 2),
                5,
                "Division failed"
        );

        // IMPORTANT: reports all collected failures
        softAssert.assertAll(); // Continues execution until assertAll() and then reports all failures together.
    }

    // Addition Data Provider
    @DataProvider(name = "additionData")
    public Object[][] additionDataProvider() {
        return new Object[][] {
                {1, 2, 3},
                {-1, -2, -3},
                {0, 0, 0},
                {100, 200, 300}
        };
    }

    @Test(dataProvider = "additionData")
    public void testAdditionWithData(int a, int b, int expected) {
        Assert.assertEquals(calculator.add(a, b), expected,
                String.format("Addition failed for %d + %d", a, b));
    }
}