package com.example.helloproject.data.entity.menu;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItems is a Querydsl query type for Items
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItems extends EntityPathBase<Items> {

    private static final long serialVersionUID = 462706766L;

    public static final QItems items = new QItems("items");

    public final com.example.helloproject.data.entity.QBaseEntity _super = new com.example.helloproject.data.entity.QBaseEntity(this);

    public final StringPath allergy = createString("allergy");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath info = createString("info");

    public final EnumPath<ItemsStatus> itemsStatus = createEnum("itemsStatus", ItemsStatus.class);

    public final EnumPath<ItemsType> itemsType = createEnum("itemsType", ItemsType.class);

    public final StringPath name = createString("name");

    public final StringPath originInfo = createString("originInfo");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final NumberPath<Long> regUser = _super.regUser;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDate = _super.updDate;

    //inherited
    public final NumberPath<Long> updUser = _super.updUser;

    public QItems(String variable) {
        super(Items.class, forVariable(variable));
    }

    public QItems(Path<? extends Items> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItems(PathMetadata metadata) {
        super(Items.class, metadata);
    }

}

