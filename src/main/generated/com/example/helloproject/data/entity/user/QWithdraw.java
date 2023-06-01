package com.example.helloproject.data.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWithdraw is a Querydsl query type for Withdraw
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWithdraw extends EntityPathBase<Withdraw> {

    private static final long serialVersionUID = -1129198896L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWithdraw withdraw = new QWithdraw("withdraw");

    public final com.example.helloproject.data.entity.QBaseEntity _super = new com.example.helloproject.data.entity.QBaseEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath reason = createString("reason");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final NumberPath<Long> regUser = _super.regUser;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDate = _super.updDate;

    //inherited
    public final NumberPath<Long> updUser = _super.updUser;

    public final QUsers users;

    public QWithdraw(String variable) {
        this(Withdraw.class, forVariable(variable), INITS);
    }

    public QWithdraw(Path<? extends Withdraw> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWithdraw(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWithdraw(PathMetadata metadata, PathInits inits) {
        this(Withdraw.class, metadata, inits);
    }

    public QWithdraw(Class<? extends Withdraw> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.users = inits.isInitialized("users") ? new QUsers(forProperty("users")) : null;
    }

}

