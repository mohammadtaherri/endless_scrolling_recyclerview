# Endless_Scroll_recyclerview

## How to download

### Maven
Step 1. Add the JitPack repository to your build file

     <repositories>
          <repository>
              <id>jitpack.io</id>
              <url>https://jitpack.io</url>
          </repository>
     </repositories>
     
Step 2. Add the dependency

     <dependency>
	    <groupId>com.github.mohamadmt</groupId>
	    <artifactId>endless_scrolling_recyclerview</artifactId>
	    <version>1.0.2</version>
	</dependency>

### Gradle: 
Add it in your root build.gradle at the end of repositories:

    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
     
Step 2. Add the dependency

     dependencies {
	        implementation 'com.github.mohamadmt:endless_scrolling_recyclerview:1.0.2'
	}


## How use this lib (with Retrofit)

### step1
       private RecyclerView recyclerView;
       private ProgressBar  progressbar;
       private ScrollObject object;

       @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
        
        setup();
    }
    
### step2    
    private void setup{

        recyclerView = findViewById(R.id.recycler_view);
        progressbar       = findViewById(R.id.progressbar);

        object = new ScrollObject.Builder()
                .setContext(this)
                .setCurrentPage(2)
                .setVisibleThreshold(6)
                .setProgressbar(progressbar)
                .setRecyclerView(recyclerView)
                .build();

        recyclerView.addOnScrollListener(new RecyclerOnScrollListener(object) {
            @Override
            protected void onLoadMore(int currentPage, int totalPages) {
                getData(currentPage);
            }
        });
        
        ....
        recyclerView.setAdapter(...);
    }

### step3
     private void getData(int page) {
     
       getRetrofitApi().getData(page).enqueue(new Callback<...>() {
            @Override
            public void onResponse(Call<...> call, Response<...> response) {
                if (response.isSuccessful()){
                    ...
                    ...

                    if (isLastPage?)
                        object.loadSuccess(false);
                    else
                        object.loadSuccess(true);
                }
            }

            @Override
            public void onFailure(Call<...> call, Throwable t) {
                object.loadFailed();
            }
        });
    }


## License
Copyright 2018 Mohammad Taheri

## Author
Mohammad Taheri

email: taherimohammad08@gmail.com

github: https://github.com/mohamadmt

