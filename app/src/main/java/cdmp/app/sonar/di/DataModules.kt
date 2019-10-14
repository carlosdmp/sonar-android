package cdmp.app.sonar.di

object DataModules {
//
//    private const val BaseUrl = "https://rickandmortyapi.com/"
//
//    val remoteServiceModule = module {
//        single {
//            OkHttpClient.Builder()
//                .connectTimeout(10L, TimeUnit.SECONDS)
//                .readTimeout(10L, TimeUnit.SECONDS).build()
//        }
//
//        single {
//            createWebService<RickMortyService>(
//                get(),
//                BaseUrl
//            )
//        }
//    }
//
//    val apiModule = module {
//        single {
//            RickMortyApi(get()) as RickMortyDataSource
//        }
//    }
//
//    val dbModule = module {
//        single {
//            Room.databaseBuilder(
//                androidContext(),
//                RickMortyDb::class.java,
//                "Database"
//            ).build() as RickMortyDataSource.Mutable
//        }
//    }
//
//    val repoModule = module {
//        single(named(DiModuleBuilder.REAL_IMPL)) {
//            RickMortyRepo(get(), get())
//        }
//    }
//}
//
//inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
//    val retrofit = Retrofit.Builder()
//        .baseUrl(url)
//        .client(okHttpClient)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//    return retrofit.create(T::class.java)
}