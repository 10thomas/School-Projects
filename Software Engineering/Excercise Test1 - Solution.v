Require Import NaturalDeduction.

Lemma L15f (P Q : Prop) : (~(P -> Q) -> P /\ ~Q) /\ (P /\ ~Q -> ~(P -> Q)).
Proof.
And_Intro.
Impl_Intro.
And_Intro.
PBC.
assume (P -> Q).
Not_Elim in H and H1.
assumption.
Impl_Intro.
Not_Elim in H0 and H1.
PBC.
assumption.
Not_Intro.
assume (P -> Q).
Not_Elim in H and H1.
assumption.
Impl_Intro.
assumption.

Impl_Intro.
Not_Intro.
And_Elim_1 in H.
Impl_Elim in H0 and H1.
And_Elim_2 in H.
Not_Elim in H2 and H3.
assumption.
Qed.

Lemma L22a (P Q : Prop) : (P /\ Q -> Q /\ P) /\ (Q /\ P -> P /\ Q).
Proof.
And_Intro.
Impl_Intro.
And_Intro.
And_Elim_2 in H.
assumption.
And_Elim_1 in H.
assumption.

Impl_Intro.
And_Intro.
And_Elim_2 in H.
assumption.
And_Elim_1 in H.
assumption.
Qed.

Lemma L22b (P Q : Prop) : (P \/ Q -> Q \/ P) /\ (Q \/ P -> P \/ Q).
Proof.
And_Intro.
Impl_Intro.
Or_Elim in H.
Or_Intro_2.
assumption.

Or_Intro_1.
assumption.

Impl_Intro.
Or_Elim in H.
Or_Intro_2.
assumption.
Or_Intro_1.
assumption.
Qed.

Lemma L22c (P : Prop) : (False -> P /\ ~P) /\ (P /\ ~P -> False).
Proof.
And_Intro.
Impl_Intro.
PBC.
assumption.

Impl_Intro.
And_Elim_all in H.
Not_Elim in H0 and H.
assumption.
Qed.

Lemma L22d (P Q R : Prop) : (P /\ (Q /\ R) -> (P /\ Q) /\ R) /\ ((P /\ Q) /\ R -> P /\ (Q /\ R)).
Proof.
And_Intro.
Impl_Intro.
And_Elim_all in H.
And_Intro.
And_Intro.
assumption.
assumption.
assumption.

Impl_Intro.
And_Elim_all in H.
And_Intro.
assumption.
And_Intro.
assumption.
assumption.
Qed.

Lemma L22e (P Q R : Prop) : (P \/ (Q \/ R) -> (P \/ Q) \/ R) /\ ((P \/ Q) \/ R -> P \/ (Q \/ R)).
Proof.
And_Intro.
Impl_Intro.
Or_Elim in H.
Or_Intro_1.
Or_Intro_1.
assumption.

Or_Elim in H.
Or_Intro_1.
Or_Intro_2.
assumption.
Or_Intro_2.
assumption.

Impl_Intro.
Or_Elim in H.
Or_Elim in H.
Or_Intro_1.
assumption.

Or_Intro_2.
Or_Intro_1.
assumption.

Or_Intro_2.
Or_Intro_2.
assumption.
Qed.

Lemma L22f (P Q R : Prop) : (P /\ (Q \/ R) -> (P /\ Q) \/ (P /\ R)) /\ ((P /\ Q) \/ (P /\ R) -> P /\ (Q \/ R)).
Proof.
And_Intro.
Impl_Intro.
And_Elim_all in H.
Or_Elim in H0.
Or_Intro_1.
And_Intro.
assumption.
assumption.
Or_Intro_2.
And_Intro.
assumption.
assumption.
Impl_Intro.
And_Intro.
Or_Elim in H.
And_Elim_1 in H.
assumption.
And_Elim_1 in H.
assumption.
Or_Elim in H.
And_Elim_2 in H.
Or_Intro_1.
assumption.
And_Elim_2 in H.
Or_Intro_2.
assumption.
Qed.

Lemma L22g (P Q R : Prop) : (P \/ (Q /\ R) -> (P \/ Q) /\ (P \/ R)) /\ ((P \/ Q) /\ (P \/ R) -> P \/ (Q /\ R)).
Proof.
And_Intro.
Impl_Intro.
And_Intro.
Or_Elim in H.
Or_Intro_1.
assumption.
And_Elim_1 in H.
Or_Intro_2.
assumption.
Or_Elim in H.
Or_Intro_1.
assumption.
And_Elim_2 in H.
Or_Intro_2.
assumption.
Impl_Intro.
And_Elim_all in H.
Or_Elim in H.
Or_Intro_1.
assumption.
Or_Elim in H0.
Or_Intro_1.
assumption.
Or_Intro_2.
And_Intro.
assumption.
assumption.
Qed.

(* The next lemma is difficult and needs L15d and L65c. Therefore, these Lemma are added here. *)

Lemma L15d (P : Prop) : P \/ ~P.
Proof.
PBC.
assume (P \/ ~P). 
Not_Elim in H and H0.
assumption.
Or_Intro_2.
Not_Intro.
assume (P \/ ~P).
Not_Elim in H and H1.
assumption.
Or_Intro_1.
assumption.
Qed.

Lemma L65c (A : Type) (P : A -> Prop) : (~(forall x, P x) -> (exists x, ~P x)) /\ ((exists x, ~P x) -> ~(forall x, P x)).
Proof.
And_Intro.
Impl_Intro.
PBC.
assume (forall x : A, P x).
Not_Elim in H and H1.
assumption.
Forall_Intro.
PBC.
assume (exists x : A, ~ P x).
Not_Elim in H0 and H2.
assumption.
Exists_Intro with x.
assumption.
Impl_Intro.
Not_Intro.
Exists_Elim in H.
Forall_Elim in H0 with x.
Not_Elim in H1 and H2.
assumption.
Qed.

Lemma L65g (A : Type) (P : A -> Prop) (Q : Prop) : ((forall x, P x \/ Q) -> (forall x, P x) \/ Q) /\ ((forall x, P x) \/ Q -> (forall x, P x \/ Q)).
Proof.
And_Intro.
Impl_Intro.
assert (H0 := L15d (forall x, P x)).
Or_Elim in H0.
Or_Intro_1.
assumption.
assert (H1 := L65c A P).
And_Elim_1 in H1.
Impl_Elim in H2 and H0.
Exists_Elim in H3.
Forall_Elim in H with x.
Or_Elim in H5.
Not_Elim in H4 and H5.
PBC.
assumption.
Or_Intro_2.
assumption.
Impl_Intro.
Forall_Intro.
Or_Elim in H.
Forall_Elim in H with x.
Or_Intro_1.
assumption.
Or_Intro_2.
assumption.
Qed.

Lemma L65i (A : Type) (P Q : A -> Prop) : ((exists x, P x \/ Q x) -> (exists x, P x) \/ (exists x, Q x)) /\ ((exists x, P x) \/ (exists x, Q x) -> (exists x, P x \/ Q x)).
Proof.
And_Intro.
Impl_Intro.
Exists_Elim in H.
Or_Elim in H0.
Or_Intro_1.
Exists_Intro with x.
assumption.
Or_Intro_2.
Exists_Intro with x.
assumption.
Impl_Intro.
Or_Elim in H.
Exists_Elim in H.
Exists_Intro with x.
Or_Intro_1.
assumption.
Exists_Elim in H.
Exists_Intro with x.
Or_Intro_2.
assumption.
Qed.

Lemma L65j (A : Type) (P : A -> Prop) (Q : Prop) : ((exists x, P x /\ Q) -> (exists x, P x) /\ Q) /\ ((exists x, P x) /\ Q -> (exists x, P x /\ Q)).
Proof.
And_Intro.
Impl_Intro.
And_Intro.
Exists_Elim in H.
Exists_Intro with x.
And_Elim_1 in H0.
assumption.
Exists_Elim in H.
And_Elim_2 in H0.
assumption.
Impl_Intro.
And_Elim_all in H.
Exists_Elim in H.
Exists_Intro with x.
And_Intro.
assumption.
assumption.
Qed.