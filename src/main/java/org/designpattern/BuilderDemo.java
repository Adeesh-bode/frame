package org.designpattern;

/*

 The object is immutable after construction

 */

class Person {
    private String name;
    private String address;
    private String email;
    private int age;


    public Person(Builder builder) {
        this.name = builder.name;
        this.address = builder.address;
        this.email = builder.email;
        this.age = builder.age;
    }

    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", address=" + address + ", email=" + email + "]";
    }

    public static class Builder{
        private String name;
        private String address;
        private String email;
        private int age;


        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Person build(){
            return new Person(this);
        }
    }


}

public class BuilderDemo {

    public static void main(String[] args) {
        Person person = new Person.Builder()
                .setName("xyz")
                .setAddress("123")
                .setAge(23)
                .setEmail("a@gmail.com")
                .build();

        System.out.println(person.getName());
        System.out.println(person.getAddress());
        System.out.println(person.getAge());
        System.out.println(person.getEmail());

        System.out.println(person.toString());

    }
}
