import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int count = 0;
    private static final int maxTry = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (count < maxTry) {
            count++;
            return true;
        }
        return false;
    }
}


/* USE

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

    int i = 0;

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testLogin() {
        i++;
        System.out.println("Execution Count: " + i);

        Assert.assertTrue(i == 3);
    }
}

 */