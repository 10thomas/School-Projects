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
Require Import Hoare.

Lemma Q1 (A B : Type) : forall (l : list A) (f : A -> B), rev (map f l) = map f (rev l).
(* You cannot use the lemma map_rev to do this proof. Use induction and the lemma map_app.
   No other lemma is needed. *)
Proof.
Forall_Intro.
Forall_Intro.
induction l.
simpl.
trivial.

simpl.
rewrite IHl.
rewrite map_app.
simpl.
trivial.
Qed.

Lemma Q2 (m n res : nat) :
{{ Even n \/ Even m}}
  If even m
  Then res ::= m 
  Else res ::= n
  Fi
{{ Even res }}.
(* In this proof you are not allowed to use the tactic Hoare_tactic or Hoare_while_tactic. 
   No lemma is needed. *)
Proof.
Hoare_if_rule.

Hoare_consequence_rule_left with (Even m).
Impl_Intro.
bool2Prop in H.
And_Elim_all in H.
assumption.
Hoare_assignment_rule.

Hoare_consequence_rule_left with (Even n).
Impl_Intro.
bool2Prop in H.
And_Elim_all in H.
Or_Elim in H.
assumption.
PBC.
Not_Elim in H0 and H.
assumption.
Hoare_assignment_rule.
Qed.

Lemma Q3 (m n r i : nat) :
{{ True }}
  r ::= n;;
  i ::= 0;;
  While (m <=? r) 
  Do
    i ::= i+1;;
    r ::= r-m
  Od
{{ n=i*m+r /\ r<m }}.
(* The lemmas mul_add_distr_r, mul_1_l, add_assoc, le_plus_minus_r and nle_gt might be helpful. 
   No other lemma is needed. *)
Proof.
Hoare_while_tactic with (n=i*m+r).
(* Prop 2 *)
Impl_Intro.
bool2Prop in H.
And_Elim_all in H.
rewrite mul_add_distr_r.
rewrite mul_1_l.
rewrite <- add_assoc.
rewrite le_plus_minus_r.
assumption.
assumption.

(*Prop 3*)
Impl_Intro.
bool2Prop in H.
And_Elim_all in H.
apply nle_gt in H0.
And_Intro.

assumption.
assumption.
Qed.