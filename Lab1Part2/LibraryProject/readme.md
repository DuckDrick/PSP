# Validatoriai
___
## Email
Top level domain ir domain pavadinimai sumaišyti vietomis.
Nesimato ilgio ribojimų nustatytu domain ir top level domain.
Local dalyje vienintelis specialus simbolis ".", nėra kitų simbolių.
Iš testų atrodo kad validus domain ir TLD yra tik "gmail.com".

## Password
Specialūs simboliai priklauso nuo to kas nurodyta testuojamoje klasėje, nėra galimybės jų nustatyti/keisti iš pačių testų. Testuose panaudotas tik vienas specialus simbolis.

## Phone
Testuose naudojami tik lietuvuviski numeriai. Visi validuojami numeriai prasideda 8.
Konfiguracija priklauso nuo konfiguracijos esančios validatoriuje, nes nera jokio būdo jos nustatyti/koreguoti is pačių testų.
addPhoneNumberPrefix metodas priima šalies kodą, nors pagal reikalavimus reikia pakesiti 8 į +370. Taip pat šis metodas yra public, tai reiškia kad jį gali pasiekti visi naudotojai, tokiu būdu turėsime metodą validatoriuje kuris modifikuoja numęri, bet nevaliduoja.
