package com.example.helloproject.data.entity.news;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNewsFile is a Querydsl query type for NewsFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNewsFile extends EntityPathBase<NewsFile> {

    private static final long serialVersionUID = -1024729939L;

    public static final QNewsFile newsFile = new QNewsFile("newsFile");

    public final com.example.helloproject.data.entity.QBaseEntity _super = new com.example.helloproject.data.entity.QBaseEntity(this);

    public final StringPath extension = createString("extension");

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> newsId = createNumber("newsId", Long.class);

    public final StringPath oriFileName = createString("oriFileName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath regId = createString("regId");

    //inherited
    public final NumberPath<Long> regUser = _super.regUser;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDate = _super.updDate;

    public final StringPath updId = createString("updId");

    //inherited
    public final NumberPath<Long> updUser = _super.updUser;

    public QNewsFile(String variable) {
        super(NewsFile.class, forVariable(variable));
    }

    public QNewsFile(Path<? extends NewsFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNewsFile(PathMetadata metadata) {
        super(NewsFile.class, metadata);
    }

}

