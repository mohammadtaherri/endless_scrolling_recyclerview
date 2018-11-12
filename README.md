# Endless_Scroll_recyclerview

## How to download
### Gradle: 
add this line to your module build.gradle (project) allproject block:

     maven { url 'https://jitpack.io' }
     
add this line to your module build.gradle (app) dependecies block:

     implementation 'com.github.mohamadmt:endless_scrolling_recyclerview:1.0.2'


## How use this lib (with Retrofit)

       private RecyclerView recyclerView;
       private ProgressBar  progressbar;
       private ScrollObject object;

       @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
        
        setup();
    }
    
    private void setup{

        recyclerView = findViewById(R.id.recycler_view);
        progressbar       = findViewById(R.id.progressbar);

        object = new ScrollObject.Builder()
                .setContext(this)
                .setCurrentPage(2)
                .setVisibleThreshold(6)
                .setProgressbar(pbMain)
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
    
     private void getData(int page) {
     
        new ApiServic().getApi().getMovies(page).enqueue(new Callback<...>() {
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
```

## License
Copyright 2018 Mohammad Taheri

## Author
Mohammad Taheri

email: taherimohammad08@gmail.com

github: https://github.com/mohamadmt

