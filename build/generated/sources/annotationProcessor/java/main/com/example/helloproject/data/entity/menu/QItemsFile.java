package com.example.helloproject.data.entity.menu;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemsFile is a Querydsl query type for ItemsFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemsFile extends EntityPathBase<ItemsFile> {

    private static final long serialVersionUID = 236251882L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemsFile itemsFile = new QItemsFile("itemsFile");

    public final com.example.helloproject.data.entity.QBaseEntity _super = new com.example.helloproject.data.entity.QBaseEntity(this);

    public final StringPath extension = createString("extension");

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QItems items;

    public final StringPath oriFileName = createString("oriFileName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final NumberPath<Long> regUser = _super.regUser;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDate = _super.updDate;

    //inherited
    public final NumberPath<Long> updUser = _super.updUser;

    public QItemsFile(String variable) {
        this(ItemsFile.class, forVariable(variable), INITS);
    }

    public QItemsFile(Path<? extends ItemsFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemsFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemsFile(PathMetadata metadata, PathInits inits) {
        this(ItemsFile.class, metadata, inits);
    }

    public QItemsFile(Class<? extends ItemsFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.items = inits.isInitialized("items") ? new QItems(forProperty("items")) : null;
    }

}

