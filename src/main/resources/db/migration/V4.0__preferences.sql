create table `LKUserPreferenceCode` (
    UserPreferenceCodeID int identity not null primary key,
    UserPreferenceCodeDescription varchar(100) not null,
    CreateDate datetime not null,
    UpdateDate datetime not null
);

create table `preferences` (
    PreferenceID int identity not null primary key,
    UserID int not null,
    UserPreferenceCodeID int not null,
    IsEnabled bit not null,
    constraint FK_UserID
    foreign key (UserID) references users(username),
    constraint FK_UserPreferenceCodeID
    foreign key (UserPreferenceCodeID) references LKUserPreferenceCode(UserPreferenceCodeID)
);

alter table `users`
drop column preferences;

alter table `users`
add PreferenceID int;