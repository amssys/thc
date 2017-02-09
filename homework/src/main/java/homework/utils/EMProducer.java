package homework.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.lang3.StringUtils;

/**
 * エンティティマネジャー生成クラス。
 * @author satake
 */
public final class EMProducer {

    /** 環境変数からパーシスタントユニット名を取得するキーの定義 */
    private static final String KEY_UNIT_NAME = "jpaUnitName";
    /** 通常のパーシスタントユニット名の定義 */
    public static final String DEFAULT_UNIT_NAME = "homework";

    /** 自インスタンス */
    private static EMProducer producer = null;

    /** エンティティマネジャーファクトリー */
    private final EntityManagerFactory emFactory;

    /**
     * 非インスタンス化のための private コンストラクタ。
     */
    private EMProducer() {
        String envUnitName = System.getProperty(KEY_UNIT_NAME);
        String unitName = StringUtils.isNotEmpty(envUnitName) ? envUnitName : DEFAULT_UNIT_NAME;
        emFactory = Persistence.createEntityManagerFactory(unitName);
    }

    /**
     * 自インスタンスを取得する。
     * @return  自インスタンス
     */
    private static EMProducer getInstance() {
        if (producer == null) {
            producer = new EMProducer();
        }
        return producer;
    }

    /**
     * エンティティマネジャーを生成する。
     * @return  エンティティマネジャー
     */
    public static EntityManager createManager() {
        return getInstance().emFactory.createEntityManager();
    }
}