package io.falcon.falcontest.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTumblrAccount is a Querydsl query type for TumblrAccount
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTumblrAccount extends EntityPathBase<TumblrAccount> {

    private static final long serialVersionUID = 142671094L;

    public static final QTumblrAccount tumblrAccount = new QTumblrAccount("tumblrAccount");

    public final StringPath accType = createString("accType");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> popularity = createNumber("popularity", Integer.class);

    public QTumblrAccount(String variable) {
        super(TumblrAccount.class, forVariable(variable));
    }

    public QTumblrAccount(Path<? extends TumblrAccount> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTumblrAccount(PathMetadata metadata) {
        super(TumblrAccount.class, metadata);
    }

}

