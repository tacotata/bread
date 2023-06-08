package com.example.helloproject.data.entity.cs;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContactFile is a Querydsl query type for ContactFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QContactFile extends EntityPathBase<ContactFile> {

    private static final long serialVersionUID = -714105701L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContactFile contactFile = new QContactFile("contactFile");

    public final com.example.helloproject.data.entity.QBaseEntity _super = new com.example.helloproject.data.entity.QBaseEntity(this);

    public final QContact contact;

    public final StringPath extension = createString("extension");

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath oriFileName = createString("oriFileName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final NumberPath<Long> regUser = _super.regUser;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDate = _super.updDate;

    //inherited
    public final NumberPath<Long> updUser = _super.updUser;

    public QContactFile(String variable) {
        this(ContactFile.class, forVariable(variable), INITS);
    }

    public QContactFile(Path<? extends ContactFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContactFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContactFile(PathMetadata metadata, PathInits inits) {
        this(ContactFile.class, metadata, inits);
    }

    public QContactFile(Class<? extends ContactFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.contact = inits.isInitialized("contact") ? new QContact(forProperty("contact")) : null;
    }

}

