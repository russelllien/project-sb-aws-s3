# Spring Boot 與 AWS S3 雲端儲存整合實踐 (Project Spring Boot AWS S3)

本專案展示了如何將 **Spring Boot** 應用程式與 **Amazon Web Services (AWS) S3** 進行深度整合，實作高效、可擴展的雲端檔案管理解決方案。

## 🌟 專案核心價值

在現代企業應用中，檔案（如合約、發票、附件）的儲存與安全性至關重要。本專案透過 AWS SDK 實作了以下核心能力：
1. **雲端整合**: 實作從本地服務端到 AWS S3 儲存桶（Bucket）的檔案傳輸。
2. **非同步與效率**: 處理檔案串流（InputStream）的上傳與下載，確保系統記憶體使用的高效性。
3. **安全性控管**: 透過 AWS IAM 憑證配置，展示對雲端資源存取權限的嚴謹控管。

## 🛠 技術棧 (Tech Stack)

* **核心框架**: Spring Boot 3.x
* **雲端服務**: Amazon Web Services (AWS) S3
* **SDK**: AWS SDK for Java (S3 Client)
* **開發工具**: Maven, IntelliJ IDEA
* **API 設計**: RESTful API (Multipart File Upload)

## 📂 核心功能解析

* **檔案上傳 (File Upload)**: 支援將本地檔案或透過 API 接收到的 `MultipartFile` 上傳至指定的 S3 Bucket。
* **檔案下載 (File Download)**: 透過 S3 物件鍵（Object Key）精準檢索並下載檔案至本地。
* **儲存桶管理**: 實作對 S3 Bucket 內物件的列舉與刪除操作。
* **配置管理**: 透過 `application.properties` 或環境變數優雅地管理 AWS Access Key 與 Secret Key。
