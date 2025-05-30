# 🍽️ Nomio - Yemek Sipariş Uygulaması

<img src="app/src/main/res/drawable/nomio_logo.png" width="200" />

Nomio, modern Android teknolojileri kullanılarak geliştirilen kullanıcı dostu bir yemek sipariş uygulamasıdır. Kullanıcılar uygulama üzerinden yemekleri görüntüleyebilir, detaylarını inceleyebilir ve sepete eklyebilir.

---
# 🙏 Teşekkür

Bu projeyi **Pupilica Android Bootcamp** kapsamında bitirme projesi olarak tamamladım.  
Bu değerli eğitimi sağladığı için **Pupilica** ekibine ve süreci son derece akıcı ve öğretici bir şekilde yönettiği için eğitmenimiz **Kasım Adalan** hocama içten teşekkür ederim.  

---

## 🚀 Özellikler

- 🏠 Ana sayfada yemek listesi
- 🔍 Gerçek zamanlı arama filtresi
- 📄 Yemek detay sayfası (fiyat, açıklama, görsel)
- 🛒 Sepete yemek ekleme ve toplam tutarı hesaplama
- 🗃️ Room veritabanı kullanımı (SQLite)
- 🧠 MVVM mimarisi ile katmanlı yapı
- 💉 Hilt ile Dependency Injection (bağımlılık yönetimi)
- 🔄 ViewModel & LiveData ile reaktif UI güncellemeleri
- 🌐 Retrofit ile API bağlantısı
- 🎨 Modern ve açık/koyu temalı kullanıcı arayüzü

---

## 🧰 Kullanılan Teknolojiler

| Teknoloji       | Açıklama                              |
|----------------|---------------------------------------|
| Kotlin         | Android uygulama dili                 |
| Room           | Yerel veritabanı yönetimi             |
| Hilt           | Dependency Injection framework'ü      |
| MVVM           | ViewModel tabanlı mimari yapı         |
| Retrofit       | REST API entegrasyonu                 |
| Data Binding   | XML ile ViewModel arasında bağ        |
| Navigation     | Fragment geçiş yönetimi               |
| RecyclerView   | Listeleme ve kart yapısı              |

---

## 🏗️ Proje Mimarisi

<img src="app/src/main/res/drawable/proje_mimarisi.png" width="200" />

---

## 📷 Ekran Görüntüleri

| Açılış                                                      | Ana Sayfa                                                                                                                                                                           | Detay Sayfası                                                                                                           | Sepet                                                                                                                                                                              |
|-------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| <img src="app/src/main/res/drawable/sc9.png" width="200" /> | <img src="app/src/main/res/drawable/sc1.png" width="200" /> <img src="app/src/main/res/drawable/sc5.png" width="200" /> <img src="app/src/main/res/drawable/sc6.png" width="200" /> | <img src="app/src/main/res/drawable/sc2.png" width="200" /> <img src="app/src/main/res/drawable/sc7.png" width="200" /> | <img src="app/src/main/res/drawable/sc3.png" width="200" /> <img src="app/src/main/res/drawable/sc1.png" width="200" /><img src="app/src/main/res/drawable/sc8.png" width="200" /> |


---

## 🎥 Tanıtım Videosu

[![Uygulama Tanıtımı](https://img.youtube.com/vi/slr8NAkOkG4.jpg)](https://www.youtube.com/watch?v=slr8NAkOkG4)

---

📦 API Bilgisi
Uygulama yemek verilerini ve sepet işlemlerini bir REST API üzerinden almaktadır. API uç noktaları Retrofit aracılığıyla bağlanır. Yemek açıklamaları ise Room veritabanında saklanır.

