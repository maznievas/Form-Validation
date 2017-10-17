package com.andrey.rxjavaformvalidation;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.acceptButton)
    Button accept;

    @BindView(R.id.login)
    EditText login;

    @BindView(R.id.password)
    EditText password;

    @BindDrawable(android.R.drawable.presence_busy)
    Drawable invalidField;

    @BindDrawable(android.R.drawable.presence_online)
    Drawable validField;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    TextView textView;
    private Observable<Integer> mObservable;
    private Observer<String> mObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        accept = (Button) findViewById(R.id.acceptButton);
//        //accept.setOnClickListener(this);
//        login = (EditText) findViewById(R.id.login);
//        password = (EditText) findViewById(R.id.password);

        ButterKnife.bind(this);
        textView = (TextView) findViewById(R.id.textView);


               // });


        RxTextView.textChanges(login)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        textView.setText(charSequence);
                    }
                });


//        RxTextView.afterTextChangeEvents(login)
//                .map(new Function<TextViewAfterTextChangeEvent, String>() {
//                    @Override
//                    public String apply(@NonNull TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) throws Exception {
//                        return textViewAfterTextChangeEvent.editable().toString();
//                    }
//                })
//                .observeOn(Schedulers.computation());

//        Observable<String> mObservable = Observable.just("fghdfhtftg");
//
//        mObservable.map(new Function<String, String>() {
//            @Override
//            public String apply(@NonNull String s) throws Exception {
//                return s + "slkdfjlfde";
//            }
//        });
//
//        Observer<String> mSubscriber = new Observer<String>() {
//
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.d("mLog", s + " length = " + s.length());
//            }
//
//            @Override
//            public void onError(Throwable t) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//
//        Observable.just("sldkfjsldf")
//                .map(s -> s + "1")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(s -> {
//                            Log.d("mLog", s + " length = " + s.length());
//                            Log.d("mLog", Thread.currentThread().getName());
//                        }
//                );
//
//        Observable.just(42, 34)
//                .map(new Function<Integer, Integer>() {
//                    @Override
//                    public Integer apply(@NonNull Integer integer) throws Exception {
//                        return integer + 5;
//                    }
//                })
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableObserver<Integer>() {
//                    @Override
//                    public void onNext(@NonNull Integer integer) {
//                        Log.d("mLog", integer.toString());
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//
//       // mObservable.subscribe(mSubscriber);
//
//        Observable<String> firstObs = Observable.just("BMW", "VW", "Mercedes")
//                .map(new Function<String, String>() {
//            @Override
//            public String apply(@NonNull String s) throws Exception {
//                return s + " FIRST obs ";
//            }
//        });
//
//        Observable<String> secondObs = Observable.just("550i", "Polo", "e220");
//
//        firstObs.mergeWith(secondObs)
//                .map(new Function<String, String>() {
//                    @Override
//                    public String apply(@NonNull String s) throws Exception {
//                        return s + "LOL";
//                    }
//                })
//                .filter(new Predicate<String>() {
//                    @Override
//                    public boolean test(@NonNull String s) throws Exception {
//                            return s.contains("FIRST");
//                    }
//                })
//                .map(new Function<String, String>() {
//                    @Override
//                    public String apply(@NonNull String s) throws Exception {
//                        return s + " FILTER";
//                    }
//                })
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        Log.d("mLog", s);
//                    }
//                });
//
//        Observable<String> markObs = Observable.fromArray(new String[]{"Toyota", "Honda", "Nissan"});
//        Observable<String> modelObs = Observable.fromArray(new String[]{"Previa", "Civic", "Silvia s13"});
//        Observable<Car> zipObs = Observable.zip(markObs, modelObs, (s, s2) -> new Car(s, s2))
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(Schedulers.io());
//
//        zipObs.subscribe(new Consumer<Car>() {
//                    @Override
//                    public void accept(Car car) throws Exception {
//                        Log.d("mLog", car.mark + " " + car.model);
//                    }
//                });

        Observable<CharSequence> loginObs = RxTextView.textChanges(login);

        loginObs.map(new Function<CharSequence, Boolean>() {
            @Override
            public Boolean apply(@NonNull CharSequence charSequence) throws Exception {
                return isValidLogin(charSequence);
            }
        }).subscribe(new Consumer<Boolean>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                Log.d("mLog", "is valid login: " + aBoolean);
                login.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, (aBoolean ? validField : invalidField), null);
            }
        });

        Observable<CharSequence> passwordObs = RxTextView.textChanges(password);
        passwordObs.map(new Function<CharSequence, Boolean>() {
            @Override
            public Boolean apply(@NonNull CharSequence charSequence) throws Exception {
                return isValidPassword(charSequence);

            }
        })
                .subscribe(new Consumer<Boolean>() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.d("mLog", "is valid login: " + aBoolean);
                        password.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, (aBoolean ? validField : invalidField), null);
                    }
                });

        Observable<Boolean> combinedObs = Observable.combineLatest(loginObs, passwordObs, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(@NonNull CharSequence charSequence, @NonNull CharSequence charSequence2) throws Exception {
                if (isValidLogin(charSequence) && isValidPassword(charSequence2))
                    return true;
                else return false;
            }
        });
        combinedObs.subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                accept.setEnabled(aBoolean);
            }
        });

      //  RxProgressBar.progress(progressBar)

//        RxView.clicks(accept)
//                .map(new Function<Void, Boolean>() {
//                    @Override
//                    public Boolean apply(@NonNull Void aVoid) throws Exception {
//                        progressBar.setVisibility(View.VISIBLE);
//                        return doSmth();
//                    }
//                })
//                //.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Object>() {
//                    @Override
//                    public void accept(Object o) throws Exception {
//                        progressBar.setVisibility(View.INVISIBLE);
//                        Log.d("mLog", "accept button clicked");
//                    }
//                });

//        progressBar.setVisibility(View.VISIBLE);
      final Observable<Boolean> firstObs = Observable.empty()
              .doOnSubscribe(new Consumer<Disposable>() {
                  @Override
                  public void accept(Disposable disposable) throws Exception {
                      Log.d("mLog", Thread.currentThread().getName() + " Progress bar is visible");
//                      progressBar.setVisibility(View.VISIBLE);
                  }
              })
              .map(new Function<Object, Boolean>() {
                  @Override
                  public Boolean apply(@NonNull Object s) throws Exception {
                      return doSmth();
                  }
              })
//              .subscribeOn(Schedulers.computation())
              .doAfterTerminate(new Action() {
                  @Override
                  public void run() throws Exception {
//                      Log.d("mLog", "Progress bar is invisible");
                      Log.d("mLog", Thread.currentThread().getName() + " Progress bar is invisible");
                      progressBar.setVisibility(View.INVISIBLE);
                  }
              });
        //firstObs.subscribe();

        Consumer<Boolean> booleanConsumer = new Consumer<Boolean>() {
            @Override
            public void accept(Boolean o) throws Exception {
                Log.d("mLog", "first observer ended");
            }
        };

        RxView.clicks(accept)
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                       // Log.d("mLog", "do on subscribe");
//                    }
//                })
//                .map(new Function<Object, Boolean>() {
//                    @Override
//                    public Boolean apply(@NonNull Object o) throws Exception {
//                        return true;
//                    }
//                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        firstObs.subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                throwable.printStackTrace();
                            }
                        });
                    }
                });

       // firstObs.subscribe();
    }

    public boolean doSmth()
    {
//        Log.d("mLog", "Sleep 3 seconds");
        Log.d("mLog", Thread.currentThread().getName() + "Sleeping thread");
//        SystemClock.sleep(3000);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean isValidPassword(CharSequence value) {
        //return value.toString().matches("^(?=.*\\d).{4,8}$");
        return value.length() >= 8;
    }

    private boolean isValidLogin(CharSequence value) {
//        return value.length() >= 5;
        return value.toString().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");

    }
}
