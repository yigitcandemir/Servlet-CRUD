# Kurum Listeleme Maven Servlet Projesi

Bu proje, **Java Servlet**, **JSP** ve **MySQL** kullanılarak geliştirilmiş basit bir **kurum listeleme uygulamasıdır**.  
Şu an için sadece **kurumları listeleme (Read)** özelliği vardır.  
İleride **ekleme, güncelleme ve silme (CRUD)** işlemleri de eklenecektir.

---

## Özellikler

- **Kurumları listeleme:** MySQL veritabanındaki kurumları JSP üzerinde tablo halinde görüntüler.  
- **MySQL entegrasyonu:** Docker veya lokal MySQL üzerinde çalışır.  
- **Maven tabanlı proje:** Bağımlılıklar Maven ile otomatik yönetilir.  
- **Tomcat üzerinde çalışır (WAR deploy).**

---

## Kullanılan Teknolojiler

- **Java 17+**  
- **Jakarta Servlet & JSP (Jakarta EE 10)**  
- **MySQL 8.x**  
- **Maven**  
- **Tomcat 10+**  
- **JSTL (JSP Tag Library)**

---

## Kurulum ve Çalıştırma

### **Veritabanı Kurulumu**

MySQL’de şu komutları çalıştırın:

```sql
CREATE DATABASE kurum_db;

USE kurum_db;

CREATE TABLE kurumlar (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    website VARCHAR(100)
);

INSERT INTO kurumlar (name, type, city, website) VALUES
('Kirikkale Belediyesi', 'Belediye', 'Kirikkale', 'http://kirikkale.bel.tr'),
('Saglik Bakanligi', 'Bakanlik', 'Ankara', 'https://saglik.gov.tr'),
('ODTU', 'Universite', 'Ankara', 'https://metu.edu.tr'),
('Kirikkale Universitesi', 'Üniversite', 'Kirikkale', 'https://kku.edu.tr'); 
```
---

## Maven ile Derleme ve Tomcat’e Deploy

### 1. Maven ile Derleme

Projeyi derlemek için terminalde, proje kök dizinine (`pom.xml` dosyasının olduğu yere) gidin ve şu komutu çalıştırın:

```bash
mvn clean package
```
Bu komut şunları yapar:
->Eski derlemeyi temizler (target klasörünü siler).  
->Tüm .java dosyalarını derler ve Maven bağımlılıklarını indirir.  
->Tomcat’te çalışacak WAR dosyasını üretir.    

### 2. Tomcat’e Deploy (Yükleme)
->Oluşan WAR dosyasını Tomcat/webapps klasörüne kopyalayın.  
->Tomcat’i başlatın:
```
startup.bat
```
->Tarayıcıdan 'http://localhost:8081/merhaba-servlet/KurumServlet' adresine gidin.
