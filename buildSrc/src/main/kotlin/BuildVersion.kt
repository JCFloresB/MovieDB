object BuildVersion {
    object Environment {
        //region App
        private const val MAJOR_VERSION = 1
        private const val MINOR_VERSION = 0
        private const val BUG_FIX_VERSION = 0

        const val MIN_SDK_VERSION = 24
        const val COMPILE_SDK_VERSION = 35
        const val TARGET_SDK_VERSION = 35
        const val APPLICATION_ID = "com.juan.carlos.flores.moviedb"
        const val APP_VERSION_CODE = MAJOR_VERSION * 1000 + MINOR_VERSION * 100 + BUG_FIX_VERSION
        const val APP_VERSION_NAME = "${MAJOR_VERSION}.${MINOR_VERSION}.$BUG_FIX_VERSION"
        //endregion
    }
}