use UserManagement



CREATE TABLE [dbo].[tblUsers]
(
	[userID] [nvarchar](50) NOT NULL primary key,
	[fullName] [nvarchar](50) NULL,
	[email] [nvarchar](50) NULL,
	[password] [nvarchar](50) NULL,
	[roleID] [nvarchar](50) NULL,
	[avatar] [varchar](255) NULL
)



CREATE TABLE [dbo].[tblProducts] (
    Pid nvarchar(50) PRIMARY KEY,
    PName nvarchar(50) NOT NULL,
    Pquantity int NOT NULL,
    Pprice int NOT NULL,
    Pimage varchar(255) NULL
)

CREATE TABLE [dbo].[tblOrder] (
	Oid int NOT NULL primary key,
	username [nvarchar](50) NOT NULL,
	email [nvarchar](50) NOT NULL,
	address varchar(255) NOT NULL,
	phone varchar(255) NOT NULL,
	total int,
)


CREATE TABLE [dbo].[tblOrderDetail] (
	ODid int NOT NULL,
	Pid nvarchar(50) ,
	Oid int ,
	quantity int,
	price int,
)

CREATE TABLE [dbo].[tblVerifyCode] (
	email [nvarchar](50) NOT NULL primary key,
	code varchar(50),
	SendCodeTime DateTime
)
DROP TABLE [dbo].[tblVerifyCode]

select * from tblUsers

INSERT [dbo].[tblUsers] ([userID], [fullName], [email], [password], [roleID], avatar) VALUES (N'admin', N'ADMIN', N'duylvd2002@gmail.com', N'1',N'AD', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg')
INSERT INTO [dbo].[tblUsers] (userID, fullName, password, roleID, avatar)
VALUES
('SE000001', 'John Smith', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000002', 'Jane Doe', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000003', 'David Johnson', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000004', 'Emily Brown', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000005', 'Michael Davis', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000006', 'Sarah Wilson', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000007', 'William Martin', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000008', 'Linda Clark', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000009', 'James Lee', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000010', 'Michelle Perez', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000011', 'Richard Taylor', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000012', 'Karen Allen', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000013', 'Christopher Hall', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000014', 'Rebecca Wright', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000015', 'Matthew Scott', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000016', 'Andrew Martinez', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000017', 'Elizabeth Hernandez', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000018', 'Daniel Young', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000019', 'Catherine Lewis', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000020', 'Ryan Green', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000021', 'Stephanie Adams', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000022', 'Nicholas Nelson', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000023', 'Victoria Brown', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000024', 'Erica Collins', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000025', 'Adam White', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000026', 'Katherine Edwards', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000027', 'Joshua Turner', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000028', 'Christine Parker', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000029', 'Brandon King', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg'),
('SE000030', 'Olivia Campbell', '1', 'US', 'https://i.pinimg.com/originals/b2/3e/a3/b23ea34c18999b1bddb2f49199cfd871.jpg');

INSERT INTO tblProducts (Pid, PName, Pquantity, Pprice, Pimage)
VALUES 
('P001', 'IPhone 13', 50, 1290, 'https://didongviet.vn/pub/media/catalog/product//i/p/iphone-13-pro-max-1tb-didongviet_1.jpg'),
('P002', 'Samsung Galaxy S21', 30, 920, 'https://totomobile.vn/wp-content/uploads/2023/02/S21-FE-xanh.jpeg'),
('P003', 'Xiaomi Redmi Note 11 Pro', 80, 340, 'https://cdn.hoanghamobile.com/i/preview/Uploads/2022/02/17/note-11pro-7.png'),
('P004', 'OPPO Reno5', 60, 510, 'https://cdn11.dienmaycholon.vn/filewebdmclnew/DMCL21/Picture//Apro/Apro_product_23770/reno-5-5g8gb128_main_64_1020.png.webp'),
('P005', 'Realme GT', 40, 605, 'https://cdn.tgdd.vn/Products/Images/42/235534/realme-gt-5g-600x600-1-600x600.jpg'),
('P006', 'Google Pixel 6', 20, 970, 'https://cdn.tgdd.vn/Products/Images/42/240166/google-pixel-6-pro-1-600x600.jpg'),
('P007', 'OnePlus 9 Pro', 70, 815, 'https://cdn.tgdd.vn/Products/Images/42/230770/oneplus-9-pro-600x600-1-600x600.jpg'),
('P008', 'Vivo X70 Pro+', 25, 1020, 'https://cdn.tgdd.vn/Products/Images/42/236978/vivo-x70-pro-plus-1-600x600.jpg'),
('P009', 'Nokia X20', 100, 210, 'https://cdn.tgdd.vn/Products/Images/42/236178/nokia-x20-600x600.jpg'),
('P010', 'Motorola Moto G50', 90, 190, 'https://cdn.tgdd.vn/Products/Images/42/236086/motorola-moto-g50-600x600-1-600x600.jpg'),
('P011', 'Sony Xperia 1 III', 30, 120, 'https://www.viettablet.com/images/thumbnails/480/516/detailed/48/sony-xperia-1-iii--cau-hinh.jpg'),
('P012', 'Lenovo Legion Phone Duel 2', 15, 760, 'https://www.viettablet.com/images/thumbnails/480/516/detailed/48/lenovo-legion-phone-duel-2-10.jpg'),
('P013', 'LG Wing', 5, 680, 'https://www.viettablet.com/images/thumbnails/480/516/detailed/46/lg-wing-1.jpg'),
('P014', 'Asus ROG Phone 5', 10, 900, 'https://www.viettablet.com/images/thumbnails/480/516/detailed/47/asus-rog-phone-5-chinh-hang-cau-hinh-gia-ban-1.jpg'),
('P015', 'HTC U20 5G', 20, 540, 'https://cdn.tgdd.vn/Products/Images/42/226173/htc-u-20-5g-200820-050833-600x600.jpg'),
('P022', 'iPhone X', 10, 1000, 'https://cdn.tgdd.vn/Products/Images/42/114115/iphone-x-64gb-hh-600x600.jpg'),
('P023', 'Samsung Galaxy S9', 10, 900, 'https://cdn.tgdd.vn/Products/Images/42/113263/samsung-galaxy-s9-black-600x600.jpg'),
('P024', 'Google Pixel 2', 10, 800, 'https://didongviet.vn/pub/media/catalog/product//g/o/google-pixel-2-xl-64gb-cu-didongviet.jpg'),
('P025', 'LG G7 ThinQ', 10, 700, 'https://cdn.tgdd.vn/Products/Images/42/162389/lg-g7-thinq-600x600.jpg'),
('P026', 'Huawei P20 Pro', 10, 850, 'http://weirdstore.vn/wp-content/uploads/2018/04/HUAWEI-P20-Pro-6-1-Inch-6GB-64GB-Smartphone-Aurora-Color-611539-.jpg'),
('P027', 'OnePlus 6T', 10, 650, 'https://cdn.tgdd.vn/Products/Images/42/193403/oneplus-6t-1-600x600.jpg'),
('P028', 'Sony Xperia XZ3', 10, 750, 'https://cdn.tgdd.vn/Products/Images/42/174094/sony-xperia-xz3-1-600x600.jpg'),
('P029', 'Xiaomi Mi 9', 10, 600, 'https://cdn.tgdd.vn/Products/Images/42/194806/xiaomi-mi-9-1-600x600.jpg'),
('P030', 'Nokia 9 PureView', 10, 800, 'https://cdn.tgdd.vn/Products/Images/42/197783/nokia-9-pureview-1-600x600.jpg'),
('P031', 'HTC U12+', 10, 700, 'https://cdn.tgdd.vn/Products/Images/42/158731/htc-u12-plus-1-600x600.jpg'),
('P032', 'Motorola Moto G6', 10, 300, 'https://cdn.tgdd.vn/Products/Images/42/153445/motorola-moto-g6-plus-1-600x600.jpg'),
('P033', 'Asus Zenfone 5Z', 10, 500, 'https://cdn.tgdd.vn/Products/Images/42/154678/asus-zenfone-5z-2018-zs620kl-2-600x600.jpg'),
('P034', 'Razer Phone 2', 10, 900, 'https://cdn.tgdd.vn/Products/Images/42/193569/razer-phone-2-600x600.jpg'),
('P035', 'BlackBerry Key2', 10, 700, 'https://sudospaces.com/thegioiblackberry-com-vn/2018/06/blackberry-key2-san-pham.jpg'),
('P036', 'Essential Phone PH-1', 10, 400, 'https://product.hstatic.net/1000238589/product/81ovomun5zl._sl1500__7f78fac415954bdc9d54812396880253.jpg'),
('P037', 'ZTE Axon 9 Pro', 10, 600, 'https://cdn.tgdd.vn/Products/Images/42/189004/zte-axon-9-pro-600x600.jpg'),
('P038', 'Lenovo Z5 Pro GT', 10, 550, 'https://http2.mlstatic.com/D_NQ_NP_653516-MLA41664239819_052020-O.jpg'),
('P039', 'Meizu 16s', 10, 450, 'https://cdn.sforum.vn/sforum/wp-content/uploads/2019/08/Meizu-16s-Pro-ra-mat-3.jpg'),
('P040', 'Vivo NEX S', 10, 650, 'https://cdn2.cellphones.com.vn/x358,webp,q100/media/catalog/product/s/a/samsung_galaxy_z_fold_4-7.jpg'),
('P041', 'Samsung Z Fold 4', 5, 650, 'https://didongviet.vn/pub/media/catalog/product//v/i/vivo-nex-s-didongviet.jpg'),
('P042', 'Xiaomi 13 Ultra', 5, 1650, 'https://genk.mediacdn.vn/139269124445442048/2023/4/13/gsmarena001-1681370375298-1681370376055320175294-1681370837516-16813708418831320196106.jpg'),
('P043', 'Realmi 6 Pro', 10, 650, 'https://cdn.tgdd.vn/Products/Images/42/214645/realme-6-pro-do-600x600.jpg')



