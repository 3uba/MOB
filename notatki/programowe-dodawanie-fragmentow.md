- zaleta korzystania z fragmenow jest mozliwosc ich dodawania, usuwania i zastepowania programowo, gdy aktywnosc juz dziala
- za te czynnosci oraz zarzadanie stosem odpowiada klasa <b>menedzera fragmentow</b>
- uzytkownik nie powinnien bezposrednio wchodzic w interakcje z menedzerem fragmentow - to jest odpowidzialnosci biblioteki jetpack navigation library
- kazda aktywnosc typu fragment activity ma dostep do menedzera fragmentow dzieki metodzie <b>getSupportFragmentManager</b>
- fragmenty moga takze zawierac inne fragmenty
- kazdy fragment wymaga <b>unikalnego identyfikatora</b>, ktorego  mozez uzyc system do jego odtworzenia, gdy aktywnosc jest wznawiana 
- do okreslenia identyfikatora moga sluzyc:
    - atrybut <b>android:id</b> - unikalny ID
    - atrybut <b>android:tag</b> - unikalny lancuch tekstowy
    - w razie braku ktoregokolwiek z powyzszych, system uzyje identyfikatora widoku kontenera

## Wyszukiwanie instancji fragmentu 
- Klasa menedzera fragmentow umozliwa wyszukanie fragmentu
```
public Fragment findFragmentById(int id)
public Fragment fingFragmentByTag(String tag)
```
## Transakcje
- Menedzer fragmentow zarzadza stosem - w odpowiedzi na dzilaalani uzytkownika moze dodawac lub usuwac ze stosu fragmenty
- zbior dokonanych zmian, traktowany jak pojedyncza jednostka jest nazywany <b>transakcja</b>
## Programowe dodawanie fragmetnow
```
<android.constraintlayout.widget.ConstraintLayout ...>
    <android.fragment.app.FragmentContainerView
        android:id="@+id/titles"
        app:layout_constraintWidth_percent='0.25'
        .. />
    <android.fragment.app.FragmentContainerView
        android:id="@+id/details"
        app:layout_constraintWidth_percent='0.75'
        ... />
</android.constraitnlayout.widget.ConstraintLayout>
```
- Tym razem z kontenerem nie jest skojarzona zadna instancja fragmentu
```
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activiy_main);
    ...
    FragmentManager fm = getSupportFramengtManager();
    
    fm.beginTransaction() 
        .add(R.id.title, TitlesFragment.class, null)
        .add(R.id.details, DetailsFragment.class, null)
        .setReorderingAllowed(true)
        .commit();
}

// (1) utowrzenie transakcji
// (2) dodawanie fragmentu
// (3) optymalizacja zmiany stanu
// (4) zatweirdzenie transakcji
```
- Fragment moga takze oferowac aktywnosci <b>swoje zachowanie</b> bez dostarczania dodatkowego ui (background behavior)
- do dodania takiego fragmentu moza uzyc metody
```
public FragmentTransaction add(Fragment fragment, String tag)
public FragmentTransaction add (
                            Class<? extends Fragment> fragmentClass,
                            Bundle argsm, String tag)
```
- poniweaz tak dodany fragment nie jest powiazny z grupa widokow aktywnosci, wiec nie ma potrzeby implementacji motody <b>onCreateView</b>
