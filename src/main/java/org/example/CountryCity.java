//package org.example;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "country_city")
//public class CountryCity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "country_city_id")
//    private Long countryCityId;
//
//    @Column(name = "city", nullable = false)
//    private String city;
//
//    @Column(name = "country", nullable = false)
//    private String country;
//
//    @Version
//    @Column(name = "version")
//    private Long version;
//
//
//    public CountryCity(){
//    }
//
//    public CountryCity(String city, String country) {
//        this.city = city;
//        this.country = country;
//    }
//
//
//    // Геттеры и сеттеры
//    public Long getCountryCityId() {
//        return countryCityId;
//    }
//
//    public void setCountryCityId(Long countryCityId) {
//        this.countryCityId = countryCityId;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public Long getVersion() {
//        return version;
//    }
//
//    public void setVersion(Long version) {
//        this.version = version;
//    }
//}
