<style>.d{color:red}</style>

- zaleta korzystania z fragmenow jest mozliwosc ich dodawania, usuwania i zastepowania programowo, gdy aktywnosc juz dziala
- za te czynnosci oraz zarzadanie stosem odpowiada klasa <b>menedzera fragmentow</b>
- uzytkownik nie powinnien bezposrednio wchodzic w interakcje z menedzerem fragmentow - to jest odpowidzialnosci biblioteki jetpack navigation library
- kazda aktywnosc typu fragment activity ma dostep do menedzera fragmentow dzieki metodzie <b>getSupportFragmentManager</b>
- fragmenty moga takze zawierac inne fragmenty
- kazdy fragment wymaga <b>unikalnego identyfikatora</b>, ktorego  mozez uzyc system do jego odtworzenia, gdy aktywnosc jest wznawiana 
- do okreslenia identyfikatora moga sluzyc:
    - atrybut <span class='d'>android:id</span> - unikalny ID
    - atrybut <span class='d'>android:tag</span> - unikalny lancuch tekstowy
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