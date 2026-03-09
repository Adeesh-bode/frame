//package org.designpattern;
//
//enum AccountType {
//    SAVING,
//    CURRENT,
//    LOAN
//}
//
//class AccountFactory {
//    private AccountFactory() {} // prevent object creation
//
//    public static Account createAccount(AccountType type,String UserId, double Balance, float InterestRate){
//        return switch(type){
//            case AccountType.SAVING -> new SavingsAccount( UserId, Balance, InterestRate);
//            case AccountType.LOAN -> new LoanAccount( UserId, Balance, InterestRate);
//            case AccountType.CURRENT -> new CurrentAccount( UserId, Balance);
//            default -> throw new IllegalArgumentException("Invalid account type");
//        };
//    }
//}
//
//public class FactoryDemo {
//    public static void main(String[] args) {
//
//        Account savings = AccountFactory.createAccount(
//                AccountType.SAVING,
//                User1Id,
//                100,
//                6
//        );
//
//        System.out.println(savings.getDetails());
//
//        Account loan = AccountFactory.createAccount(
//                AccountType.LOAN,
//                User1Id,
//                100,
//                13
//        );
//
//        System.out.println(loan.getDetails());
//    }
//}
