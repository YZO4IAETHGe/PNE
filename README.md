PNEditor (Petri Net editor)
========

The original PNE can be downloaded from [www.pneditor.org](http://www.pneditor.org/)

This instance is the result of a student project by Joris Thaveau for teaching purpose.

It is a simplified Petri net editor that allows the editing of many PetriNet models.

To use:

1. Run org.pneditor.editor.Main as a Java application
2. Select the model used (the menu scans the org.pneditor.petrinet.adapters folder to build a list of available models and adapters). initial and imta are available. Places and transitions are displayed in different ways.
3. Edit the PetriNet and fire transitions.

You may experiment some unexpected exceptions. Especially if you mix models.

The pedagogical approach consists in:

1. Develop your own PetriNet model in an independent project/environment - with no GUI, just the ''business'' view
2. Pack it as a jar, and let it be visible in the path
3. Develop an Adapter in the org.pneditor.petrinet.adapters folder of PNE to make your model editable

The adapter may be simple or complex depending on the "distance" between your model and the one expected by PNE.

Code license: [GNU GPL v3](http://www.gnu.org/licenses/gpl.html)

========

Ce projet est une implémentation en Java d'un réseau de Petri, un modèle mathématique utilisé pour représenter des systèmes travaillant sur des variables discrètes. Un réseau de Petri se compose de places, transitions et arcs, chacun jouant un rôle dans la simulation de la dynamique des systèmes.

Pré-requis: JUnit5 (pour les tests)

Version eclipse : 2023-09 
Version JRE : JDK 21 JDK compliance level 20
Version Java : Java SE 8+

Structure du réseaux de Petri : 
Main : Point d'entrée du programme, gérant l'initialisation et l'exécution de la simulation du réseau de Petri. 
PetriNet : Classe contenant les places et transitions. elle contient des méthodes permettant d'ajouter et supprimer des places ou transition. 
Place : représente un emplacement dans le réseau, où les jetons peuvent être stockés. 
Transition : représente une transition, activée sous certaines conditions basées sur les jetons présents dans les places entrant. 
Arc : classe abstraite représentant un arc générique connectant une place et une transition. 
InArc : un arc entrant reliant une place à une transition. 
OutArc : un arc sortant reliant une transition à une place. 
InhibitorArc : un arc d'inhibition qui peut etre activé seulement si aucun jeton est présent dans la place. 
ClearingArc : un arc de remise à zéro, s'activant seulement si une il y a au moins un jeton dans la place et supprime les jetons d'une place une fois la transition déclenchée.

Adapter :
Les classes adapters permet de rendre compatible le code de notre réseaux de Petri avec PNEditor pour permettre l'affichage et la modification depuis un interface graphique. L'adaptateur agit comme une interface intermédiaire qui traduit les données et les actions entre les deux systèmes, en s'assurant qu'ils fonctionnent ensemble malgré leurs différences structurelles et fonctionnelles.

Pour lancer l'interface graphique, il suffit de lancer le fichier Main.java situé dans src/org.pneditor.editor.

Notre implémentation n'est pas conforme à notre diagramme de classe initial. En effet, au départ nous hésitions sur l'utilité de rajouté une class mère Arc, mais nous avons finalement décidé de faire une classe mère Arc afin de vérifier que la place et le poids donnés au constructeur soient conforme (Place non nulle et poids posiif) dans le but de factoriser le code.

Nous avons rajouté un attribut id pour les classes Place et Transition afin de pouvoir distinguer facilement ces objets notamment pour la suppression et la gestion des doublons d'arcs.

Enfin nous avons rajouter des méthodes dans la classe PetriNet pour ajouter les Arcs, les Places et les Transitions et aussi pour les supprimer.