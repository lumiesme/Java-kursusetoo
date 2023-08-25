# Hangman 2023

Mäng Hangman 2023 olemasolev variant on loodud õpilastele mängu mängimise funktsionaaluse
lisamiseks.

# Hetkel toimiv või osaliselt toimiv funktsionaalsus

- Nupp **New Game**        - Reaalne aeg jäetakse seisma ja selle asemel hakatakse näitama mängu aega. Muud nupud ja sisestuskastil muudetakse juuredpääsetavust. (controllers.listeners.ButtonNew)  
- Nupp **Leaderboard**     - Näitab andmebaasi tabeli sisu, kui tabel sisaldab andmebaasis kirjeid (hetkel on see tühi) (controllers.listeners.ButtonScores)
- Nupp **Cancel the game** - Mängu aeg jäetakse seisma ja näidatakse reaalset aega. Mängu aega ei nullita. Muud nupud ja sisestuskastil muudetakse juuredpääsetavust ja vaikimisi tekst muudetakse LET's PLAY. (controllers.listeners.ButtonCancel)
- Tekstiväli lubab sisestada ainult ühte tähte/märki (helpers.TextFieldLimit)
- ComboBox (*Liitkast*) kkategooriale muudab muutuja väärtuse mudelis, et uue mängu jaoks oleks teada mis kategooria sõnu kasutada. (controllers.listeners.CategoryItemChange)
- Kõik vajalikud GUI elemndid on paneelidele pandud.
- Nupp **Send** - on olemas kuid kogu funktsionaalsus on puudu (controllers.listeners.ButtonSend)

# Ülesanne
Siia alla on toodud punktide kaupa mida peab tegema. Järjekord ei ole ilmselt loogiline, kuid üldeesmärk on mäng tööle 
saada. Vihjeks võiks olla Pythonis tehtud Hangman lahendus.

## New Game nupp 
1. Võta andmebaasi tabelist üks juhuslik sõna vastavalt kasutaja valitud kategooriale. Üks kindel või kõikide seast.
2. Joonista GameResult paneeli sildile (Label) sõna, kus iga täht on üks allkriips.
3. Juhuslik sõna ja tähed, mis on arvatud või mitte arvatud, tuleb endal meeles pidada.
4. Käivita mängu aeg ja peata reaalne aeg (*peaks olema juba tehtud*)

## Send nupp
1. Tegevus toimub ainult siis, kui tekstiväli (txtChar) on täidetud. Kui on tühi, siis ei juhtu midagi.
2. Kontrolli kas sisestatud täht on olemas.
3. Väljasta sõna vastavalt kasutaja tuvastatud tähtedele.
4. Kui tähte pole ära arvatavas sõnas, siis pane täht kirja kui vale täht.
5. Vale tähe puhul muuda pilti.
6. Vale tähe puhul muuda GameBoard paneelil olevat silti **lblError** "Wrong X letter(s): A, B, T".
7. Tähed **lblResult** ja **lblError** on trükitähed. Viimasel ainult tähed muu tekst nii nagu originaalis.
8. Kui kõik tähed on arvatud peata aeg ja näita reaalset aega (*peaks olema juba tehtud*).
9. Kui mäng on läbi, siis küsi kasutajalt mängija nime ja lisa vajalik info tabelisse **scores**
   1.  Korrektsed andmed tabelisse scores lisamiseks vaadata näidis faili hangman_words_ee_test.db edetabeli tabelit.
10. Kui mäng katkestatakse, siis peatatakse mängu aeg, näidatakse reaalset aega ja muudetakse pilt viimaseks (hang11.png). Äraarvatav sõna muuta sõnaks LET'S PLAY (*see viimane on tegemata*).
11. Kui tähte on sisestatud üle ühe korra, siis loe seda kui viga, kuigi esimesel korral oli olemas aga ära näita vigaste tähtede seas.

## Dokumentatsioon
1. Kõik teie kirjutatud meetodid peavad sisaldama JavaDoc\'i.
2. Kui meetod on kopeeritud internetist, siis peab see sisaldama linki/viidet algallikale