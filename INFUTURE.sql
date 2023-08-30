CREATE DATABASE infuture;
USE infuture;
SET SQL_SAFE_UPDATES =1;

CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    fullname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    bio TEXT,
    profile_picture VARCHAR(255)
);
INSERT INTO Users (username, fullname, email, password, bio, profile_picture)
VALUES('user', 'user', 'user@gmail.com','user','Đây là tiểu sử của user 📷✈️🏕️','avatar.jpg');
INSERT INTO Users (username, fullname, email, password, bio, profile_picture)
VALUES('quocdoanh', 'Nguyễn Quốc Doanh', '123@gmail.com','123','Đây là tiểu sử của Nguyễn Quốc Doanh 📷✈️🏕️','doanh.jpg');
INSERT INTO Users (username, fullname, email, password, bio, profile_picture)
VALUES('ducthien', 'Nguyễn Đức Thiện','thien@gmail.com','123','Đây là tiểu sử của Nguyễn Đức Thiện 📷✈️🏕️','thien.jpg');
INSERT INTO Users (username, fullname, email, password, bio, profile_picture)
VALUES('giahan', 'Gia Hân','giahan@gmail.com','123','Đây là tiểu sử của Gia Hân 📷✈️🏕️','giahan.jpg');


INSERT INTO Users (username, fullname, email, password, bio, profile_picture)
VALUES('baolong', 'Bảo Long', 'baolong@gmail.com','123','Đây là tiểu sử của Bảo Long 🚀🌈️','baolong.jpg');
INSERT INTO Users (username, fullname, email, password, bio, profile_picture)
VALUES('thienan', 'Thiên An','thienan@gmail.com','123','Đây là tiểu sử của Thiên An 📷🌺💡️','thienan.jpg');
INSERT INTO Users (username, fullname, email, password, bio, profile_picture)
VALUES('duongqua', 'Dương Quá','duongqua@gmail.com','123','Đây là tiểu sử của Dương Quá 🐶💡💡🏕️','duongqua.jpg');
INSERT INTO Users (username, fullname, email, password, bio, profile_picture)
VALUES('tieulongnu', 'Tiểu Long Nữ', 'tieulongnu@gmail.com','123','Đây là tiểu sử của Tiểu Long Nữ 📷✈⚽️🎸💡💡🎸️🏕️','tieulongnu.jpg');
INSERT INTO Users (username, fullname, email, password, bio, profile_picture)
VALUES('quachtinh', 'Quách Tĩnh','quachtinh@gmail.com','123','Đây là tiểu sử của Quách Tĩnh 📷🎸🎸✈️💡💡🏕️','quachtinh.jpg');



CREATE TABLE Posts (
    post_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    image_url VARCHAR(255),
    caption TEXT,
    created_at DATETIME,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

INSERT INTO Posts (user_id, image_url, caption, location, created_at)
VALUES(24,'giahan1.jpg','Đây là bức ảnh đầu tiên của mình 📷✈️🏕️','Hà Nội',NOW());
INSERT INTO Posts (user_id, image_url, caption, location, created_at)
VALUES(7,'doanh1.jpg','Đây là bức ảnh đầu tiên của mình 📷✈️🏕️','Hà Nội',NOW());
INSERT INTO Posts (user_id, image_url, caption, location, created_at)
VALUES(7,'doanh2.jpg','Đây là bức ảnh thứ 2 của mình 📷✈️🏕️','Hà Nội',NOW());
INSERT INTO Posts (user_id, image_url, caption, location, created_at)
VALUES(7,'doanh3.jpg','Đây là bức ảnh thứ 3 của mình 📷✈️🏕️','Hà Nội',NOW());
INSERT INTO Posts (user_id, image_url, caption, location, created_at)
VALUES(7,'doanh4.jpg','Đây là bức ảnh thứ 4 của mình 📷✈️🏕️','Hà Nội',NOW());
INSERT INTO Posts (user_id, image_url, caption, location, created_at)
VALUES(7,'doanh5.jpg','Đây là bức ảnh thứ 5 của mình 📷✈️🏕️','Hà Nội',NOW());
INSERT INTO Posts (user_id, image_url, caption, location, created_at)
VALUES(7,'doanh6.jpg','Đây là bức ảnh thứ 6 của mình 📷✈️🏕️','Hà Nội',NOW());
INSERT INTO Posts (user_id, image_url, caption, location, created_at)
VALUES(7,'doanh7.jpg','Đây là bức ảnh thứ 7 của mình 📷✈️🏕️','Hà Nội',NOW());

INSERT INTO Posts (user_id, image_url, caption, location, created_at)
VALUES(8,'giahan1.jpg','Đây là bức ảnh đầu tiên của mình 📷✈️🏕️','Hà Nội',NOW());
INSERT INTO Posts (user_id, image_url, caption, location, created_at)
VALUES(8,'giahan2.jpg','Đây là bức ảnh thứ 2 của mình 📷✈️🏕️','Hà Nội',NOW());
INSERT INTO Posts (user_id, image_url, caption, location, created_at)
VALUES(8,'giahan3.jpg','Đây là bức ảnh thứ 3 của mình 📷✈️🏕️','Hà Nội',NOW());
INSERT INTO Posts (user_id, image_url, caption, location, created_at)
VALUES(8,'giahan4.jpg','Đây là bức ảnh thứ 4 của mình 📷✈️🏕️','Hà Nội',NOW());
INSERT INTO Posts (user_id, image_url, caption, location, created_at)
VALUES(8,'giahan5.jpg','Đây là bức ảnh thứ 5 của mình 📷✈️🏕️','Hà Nội',NOW());
INSERT INTO Posts (user_id, image_url, caption, location, created_at)
VALUES(8,'giahan6.jpg','Đây là bức ảnh thứ 6 của mình 📷✈️🏕️','Hà Nội',NOW());
INSERT INTO Posts (user_id, image_url, caption, location, created_at)
VALUES(8,'giahan7.jpg','Đây là bức ảnh thứ 7 của mình 📷✈️🏕️','Hà Nội',NOW());

INSERT INTO Posts (user_id, image_url, caption, location, created_at)
VALUES(9,'thien8.jpg','Đây là bức ảnh đầu tiên của mình 📷✈️🏕️','Hà Nội',NOW());
INSERT INTO Posts (user_id, image_url, caption, location, created_at)
VALUES(9,'thien2.jpg','Đây là bức ảnh thứ 2 của mình 📷✈️🏕️','Hà Nội',NOW());
INSERT INTO Posts (user_id, image_url, caption, location, created_at)
VALUES(9,'thien3.jpg','Đây là bức ảnh thứ 3 của mình 📷✈️🏕️','Hà Nội',NOW());
INSERT INTO Posts (user_id, image_url, caption, location, created_at)
VALUES(9,'thien4.jpg','Đây là bức ảnh thứ 4 của mình 📷✈️🏕️','Hà Nội',NOW());
INSERT INTO Posts (user_id, image_url, caption, location, created_at)
VALUES(9,'thien5.jpg','Đây là bức ảnh thứ 5 của mình 📷✈️🏕️','Hà Nội',NOW());
INSERT INTO Posts (user_id, image_url, caption, location, created_at)
VALUES(9,'thien6.jpg','Đây là bức ảnh thứ 6 của mình 📷✈️🏕️','Hà Nội',NOW());
INSERT INTO Posts (user_id, image_url, caption, location, created_at)
VALUES(9,'thien7.jpg','Đây là bức ảnh thứ 7 của mình 📷✈️🏕️','Hà Nội',NOW());

CREATE TABLE Follows (
    follow_id INT AUTO_INCREMENT NOT NULL,
    follower_id INT NOT NULL,
    followed_id INT NOT NULL,
    PRIMARY KEY (follow_id),
    UNIQUE (follower_id, followed_id),
    FOREIGN KEY (follower_id) REFERENCES Users(user_id),
    FOREIGN KEY (followed_id) REFERENCES Users(user_id)
);

INSERT INTO Follows (follower_id, followed_id)
VALUES (7, 9);
INSERT INTO Follows (follower_id, followed_id)
VALUES (7, 8);
INSERT INTO Follows (follower_id, followed_id)
VALUES (8, 7);
INSERT INTO Follows (follower_id, followed_id)
VALUES (9, 7);


CREATE TABLE Comments (
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    text TEXT NOT NULL,
    created_at DATETIME,
    FOREIGN KEY (post_id) REFERENCES Posts(post_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);


INSERT INTO Comments (post_id, user_id, text, created_at)
VALUES (10, 8, 'Bức ảnh này rất đẹp!', NOW());
INSERT INTO Comments (post_id, user_id, text, created_at)
VALUES (10, 9, 'Bài viết hay quá!', NOW());
INSERT INTO Comments (post_id, user_id, text, created_at)
VALUES (10, 24, 'Tôi rất yêu bứa ảnh này!', NOW());

INSERT INTO Comments (post_id, user_id, text, created_at)
VALUES (11, 26, 'Bài viết hay quá!', NOW());
INSERT INTO Comments (post_id, user_id, text, created_at)
VALUES (11, 25, 'Tôi rất yêu bứa ảnh này!', NOW());

INSERT INTO Comments (post_id, user_id, text, created_at)
VALUES (12, 8, 'Bài viết hay quá!', NOW());
INSERT INTO Comments (post_id, user_id, text, created_at)
VALUES (12, 9, 'Bài viết hay quá!', NOW());
INSERT INTO Comments (post_id, user_id, text, created_at)
VALUES (12, 26, 'Bài viết hay quá!', NOW());
INSERT INTO Comments (post_id, user_id, text, created_at)
VALUES (14, 9, 'Bài viết hay quá!', NOW());

CREATE TABLE Likes (
	like_id INT AUTO_INCREMENT NOT NULL,
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (like_id),
    UNIQUE (post_id, user_id),
    FOREIGN KEY (post_id) REFERENCES Posts(post_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
INSERT INTO Likes (post_id, user_id)
VALUES (10, 7);
INSERT INTO Likes (post_id, user_id)
VALUES (11, 7);
INSERT INTO Likes (post_id, user_id)
VALUES (12, 7);
INSERT INTO Likes (post_id, user_id)
VALUES (13, 7);
INSERT INTO Likes (post_id, user_id)
VALUES (10, 8);
INSERT INTO Likes (post_id, user_id)
VALUES (11, 8);
INSERT INTO Likes (post_id, user_id)
VALUES (12, 8);
INSERT INTO Likes (post_id, user_id)
VALUES (13, 8);
INSERT INTO Likes (post_id, user_id)
VALUES (10, 9);
INSERT INTO Likes (post_id, user_id)
VALUES (11, 9);
INSERT INTO Likes (post_id, user_id)
VALUES (12, 9);
INSERT INTO Likes (post_id, user_id)
VALUES (13, 9);


CREATE TABLE PrivateMessages (
    message_id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id INT,
    receiver_id INT,
    text TEXT,
    image_url VARCHAR(255),
    created_at DATETIME,
    FOREIGN KEY (sender_id) REFERENCES Users(user_id),
    FOREIGN KEY (receiver_id) REFERENCES Users(user_id)
);