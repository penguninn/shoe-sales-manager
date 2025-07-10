# Quản Lý Shop Giày

Phần mềm quản lý cửa hàng giày dép với giao diện hiện đại và đầy đủ tính năng quản lý.

## Tổng Quan

Phần mềm Quản Lý Shop Giày là ứng dụng Java Swing được thiết kế để quản lý toàn diện hoạt động của cửa hàng bán giày. Ứng dụng sử dụng giao diện người dùng đồ họa hiện đại với hiệu ứng gradient và thiết kế thân thiện với người dùng.

## Tính Năng Chính

### 1. Quản Lý Sản Phẩm
- Thêm, sửa, xóa thông tin giày dép
- Quản lý thương hiệu
- Quản lý loại sản phẩm
- Quản lý kích cỡ
- Quản lý màu sắc
- Quản lý chất liệu

### 2. Quản Lý Khách Hàng
- Lưu trữ thông tin khách hàng
- Tìm kiếm khách hàng

### 3. Quản Lý Đơn Hàng
- Tạo đơn hàng mới
- Theo dõi trạng thái đơn hàng
- Xem chi tiết đơn hàng

### 4. Quản Lý Nhân Viên
- Thêm, sửa, xóa thông tin nhân viên
- Quản lý quyền truy cập

### 5. Bán Hàng
- Thực hiện bán hàng trực tiếp
- Tính toán tổng tiền đơn hàng

### 6. Báo Cáo
- Xem báo cáo doanh thu
- Thống kê sản phẩm
- Thống kê khách hàng
- Thống kê nhân viên

## Cài Đặt

### Yêu Cầu Hệ Thống
- Java SDK 23
- Maven

### Hướng Dẫn Cài Đặt
1. Clone hoặc tải xuống repository
2. Mở project trong IDE (NetBeans 23)
3. Cài đặt dependencies thông qua Maven
4. Build project

```bash
mvn clean install
```

## Chạy Ứng Dụng

### Từ IDE
Chạy class `com.zzz.quanlibangiay.Quanlibangiay` với main method

### Sử Dụng Maven
```bash
mvn exec:java
```

## Đăng Nhập

### Thông Tin Tài Khoản
- **Tên đăng nhập:** `admin`
- **Mật khẩu:** `admin`

## Cấu Trúc Dữ Liệu

Dữ liệu được lưu trữ trong các file XML:
- Shoes.xml - Thông tin giày dép
- Brands.xml - Thương hiệu
- Types.xml - Loại sản phẩm
- Sizes.xml - Kích cỡ
- Colors.xml - Màu sắc
- Materials.xml - Chất liệu
- Customers.xml - Khách hàng
- Orders.xml - Đơn hàng
- OrderItems.xml - Chi tiết đơn hàng
- Users.xml - Nhân viên

## Công Nghệ Sử Dụng

- Java Swing cho UI
- FlatLaf cho giao diện hiện đại
- JAXB để làm việc với XML
- MigLayout và các thành phần UI tùy chỉnh

---

© 2025 - Quản Lý Shop Giày - All Rights Reserved