package com.example.helloproject.data.entity.store;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStoreFile is a Querydsl query type for StoreFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreFile extends EntityPathBase<StoreFile> {

    private static final long serialVersionUID = 2140742963L;

    public static final QStoreFile storeFile = new QStoreFile("storeFile");

    public final com.example.helloproject.data.entity.QBaseEntity _super = new com.example.helloproject.data.entity.QBaseEntity(this);

    public final StringPath extension = createString("extension");

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath oriFileName = createString("oriFileName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final NumberPath<Long> regUser = _super.regUser;

    public final NumberPath<Long> storeId = createNumber("storeId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDate = _super.updDate;

    //inherited
    public final NumberPath<Long> updUser = _super.updUser;

    public QStoreFile(String variable) {
        super(StoreFile.class, forVariable(variable));
    }

    public QStoreFile(Path<? extends StoreFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStoreFile(PathMetadata metadata) {
        super(StoreFile.class, metadata);
    }

}

