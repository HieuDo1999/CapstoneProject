package entity.person;

abstract class Person {
    public String name;
    public String password;
    public String email;
    public String age;
    public String phone_numbers;
    public String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone_numbers() {
        return phone_numbers;
    }

    public void setPhone_numbers(String phone_numbers) {
        this.phone_numbers = phone_numbers;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @param name
     * @param password
     * @param email
     * @param age
     * @param phone_numbers
     * @param address
     */
    public Person(String name, String password, String email, String age, String phone_numbers, String address) {
        super();
        this.name = name;
        this.password = password;
        this.email = email;
        this.age = age;
        this.phone_numbers = phone_numbers;
        this.address = address;
    }

}
