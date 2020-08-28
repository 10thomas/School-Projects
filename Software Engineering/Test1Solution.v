(* 
Please do the following:
1. Add the following information:
   Name:
   Std#:
2. Create a folder with your student number
   on the desktop.
2. Rename this file to Testxxxxxxx where
   xxxxxxx is your student number and
   save it in the folder you have created above.
4. Proof the lemmas below.
5. Once you are finished sent the folder (right click >
   Sent to) to the s: drive.
6. Please note that you are only allowed to use tactics
   that have been introduced in the class/labs.
*)

Require Import NaturalDeduction.

Lemma Q1 (P Q R : Prop) : (P -> R) /\ (Q -> R) -> (P \/ Q -> R).
(* Hint: The proof does not require PBC nor any application of assume.
*)
Proof.
Impl_Intro.
Impl_Intro.
Or_Elim in H0.
And_Elim_1 in H.
Impl_Elim in H1 and H0.
assumption.
And_Elim_2 in H.
Impl_Elim in H1 and H0.
assumption.
Qed.


Lemma Q2 (P Q : Prop) : ((P -> Q) -> (~Q -> ~P)) /\ ((~Q -> ~P) -> (P -> Q)).
(* Hint: The first implication can be shown without using PBC. The second implication 
         requires one application of PBC on the goal Q. Assume is not needed at all.
*)
Proof.
And_Intro.
Impl_Intro.
Impl_Intro.
Not_Intro.
Impl_Elim in H and H1.
Not_Elim in H0 and H3.
assumption.

Impl_Intro.
Impl_Intro.
PBC.
Impl_Elim in H and H1.
Not_Elim in H3 and H0.
assumption.
Qed.

Lemma Q3 (A : Type) (P Q : A -> Prop) : ((exists x, P x \/ Q x) -> ((forall x, ~P x) -> (exists x, Q x))).
(* Hint: The proof needs an application of PBC. This is needed once you have already False as an assumption.
         Assume is not needed at all.
*)
Proof.
Impl_Intro.
Impl_Intro.
Exists_Elim in H.
Forall_Elim in H0 with x.
Or_Elim in H1.
Not_Elim in H3 and H1.
PBC.
assumption.

Exists_Intro with x.
assumption.
Qed.
