# Fragmenty

```
public static class DetailsFragment extends Framgent {
    public DeatilsFragment () {
        super(R.layout.detail_fragment)
    }
}

```

- Widotk fragmentu mozna tez zbudowac w metodzie ```onCreateView```
- Jesli uklad widokow jest zdefiniowany w XML, to do jego zainstancjowania mozna uzyc metode inflate
- Fragment musi byc osadzony wewnatrz klasy aktywnosci ```android.fragmeny.FragmentActivity```(jest to klasa bazowa m.in. dla ```android.fragment.app.AppCompatActivity)```
- Aby go dolaczyc do hierarchiii widokow aktywnosci trzeba go umiescic <b>wewnatrz kontenera</b>```androidx.appcompat.app.FragmentContainerView```
- Jezeli fragment bedzie dolaczany dynamicznie, rola kontenera jest rezerwacja miejsca wstawienia fragmentu
- Aby moc reagowac na zdarzenia uzytkownika lib wspoldzelic infromacje o stanie, potrzebny jests sposob komuniacji pomiedzy fragmentami lub fragmentami a aktywnoscia
- Fragmenty nie powinny sie komunikowac <b>bezposrenio</b> miedzy soba, ani aktywnoscia
- Biblioteka fragmentow udostepnia diw metody komunikacji:
    - wspoldzielenie danych za pomoca obietkow ```ViewModel```
    - Fragment Resul API _ gdy trzeba jednakowo przekazac dana
 
## Definicja obiektu wspoldzielonych danych
```
public class DataViewModel extends ViewModel {
    private final MutableLiveData<String> data =
                                            new MutableLiveData<>();

    public void setSharedText (String newTest) {
        data.setValue(newText);
    }

    public LiveData<String> getSharedText() {
        return data;
    }
}
```


## Zmiana danych (fragment A)
```
DataViewModel dataVM = new ViewModelProvider (requireActivity())
                                            .get(DataViewModel.class);
LiveData<String> liveData = dataVM.getSharedText();
liveData.setValue("Nowy komuniakat");
```

## Reakcja na zmiane danych (fragment B - listener)
```
DataViewModel dataVM = new ViewModelProvider(requireActivity())
                                            .get(DataViewModel.class);
dataVM.getSharedText()
        .observer(getViewLifecycleOwner(), s -> {
            //aktualizacja UI
});
```
