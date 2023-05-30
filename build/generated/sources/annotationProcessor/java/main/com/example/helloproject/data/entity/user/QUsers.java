package com.example.helloproject.data.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUsers is a Querydsl query type for Users
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUsers extends EntityPathBase<Users> {

    private static final long serialVersionUID = -1800842238L;

    public static final QUsers users = new QUsers("users");

    public final com.example.helloproject.data.entity.QBaseEntity _super = new com.example.helloproject.data.entity.QBaseEntity(this);

    public final StringPath birthday = createString("birthday");

    public final StringPath birthmonth = createString("birthmonth");

    public final StringPath birthyear = createString("birthyear");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath mobile = createString("mobile");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath picture = createString("picture");

    public final BooleanPath promotionAgree = createBoolean("promotionAgree");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final NumberPath<Long> regUser = _super.regUser;

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final StringPath snsId = createString("snsId");

    public final StringPath snsType = createString("snsType");

    public final StringPath storeAddress = createString("storeAddress");

    public final StringPath storeName = createString("storeName");

    public final StringPath storeTel = createString("storeTel");

    public final StringPath team = createString("team");

    public final StringPath teamTel = createString("teamTel");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDate = _super.updDate;

    //inherited
    public final NumberPath<Long> updUser = _super.updUser;

    public QUsers(String variable) {
        super(Users.class, forVariable(variable));
    }

    public QUsers(Path<? extends Users> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUsers(PathMetadata metadata) {
        super(Users.class, metadata);
    }

}

