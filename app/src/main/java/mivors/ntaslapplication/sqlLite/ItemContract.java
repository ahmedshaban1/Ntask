package mivors.ntaslapplication.sqlLite;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Ahmed shaban on 12/2/2017.
 */

public class ItemContract {

    public static final class ItemEntry implements BaseColumns {

        // item table and column names
        public static final String TABLE_NAME = "item";

        // Since ItemEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHOTOURL = "photoUrl";



    }
}
