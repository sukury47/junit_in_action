package com.github.sukury47.junit_in_action.extras;

import java.util.Arrays;

public class User {
    private String name;
    private long id;
    private int age;
    private float weight;
    private boolean isMember;
    private double credits;
    private String[] interests;

    public static class Builder {
        private String name;
        private long id;
        private int age;
        private float weight;
        private boolean isMember;
        private double credits;
        private String[] interests;
        
        public Builder() {

        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder weight(float weight) {
            this.weight = weight;
            return this;
        }

        public Builder isMember(boolean isMember) {
            this.isMember = isMember;
            return this;
        }

        public Builder credits(double credits) {
            this.credits = credits;
            return this;
        }

        public Builder interests(String[] interests) {
            this.interests = interests;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public User(Builder builder) {
        this.name = builder.name;
        this.id = builder.id;
        this.age = builder.age;
        this.weight = builder.weight;
        this.isMember = builder.isMember;
        this.credits = builder.credits;
        this.interests = builder.interests;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result * name.hashCode();
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + age;
        result = 31 * result + Float.floatToIntBits(weight);
        result = 31 * result + (isMember ? 1 : 0);            
        long creditsLong = Double.doubleToLongBits(credits);
        result = 31 * result + (int)(creditsLong ^ (creditsLong >>> 32));
        result = 31 * result + Arrays.hashCode(interests);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User _obj = (User)obj;
            return
                this.name.equals(_obj.name) &&
                this.id == _obj.id &&
                this.age == _obj.age &&
                this.weight == _obj.weight &&
                this.isMember == _obj.isMember &&
                this.credits == _obj.credits &&
                Arrays.equals(this.interests, _obj.interests);
        }
        return false;
    }
}
