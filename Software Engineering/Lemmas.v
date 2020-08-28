Require Import Hoare.

Lemma Even_succ : forall (n : nat), ~ Even n -> Even (n + 1).
Proof.
intros.
rewrite add_1_r.
rewrite Even_succ.
assert (H0 := Even_or_Odd n).
destruct H0.
contradiction.
assumption.
Qed.

Lemma ne0_ge1 : forall (n : nat), n <> 0 <-> 1 <= n.
Proof.
intros; split; intros.
case_eq n; intros.
contradiction.
apply le_n_S.
apply le_0_n.
case_eq n; intros.
intro.
rewrite H0 in H.
apply (le_antisymm _ _ (le_0_n 1)) in H.
inversion H.
rewrite <- H0.
intro.
rewrite H1 in H.
apply (le_antisymm _ _ (le_0_n 1)) in H.
inversion H.
Qed.

Lemma lt_le_succ (m n : nat) : m < n <-> m+1 <= n.
Proof.
rewrite add_1_r.
rewrite le_succ_l.
reflexivity.
Qed.

Lemma pow_succ : forall (x y : nat), x^(y+1) = x*x^y.
Proof.
intros.
rewrite add_1_r.
rewrite pow_succ_r'.
trivial.
Qed.

Lemma sub_sub_assoc : forall (m n p : nat), p <= n <= m -> m - (n - p) = m - n + p.
Proof.
induction m.
intros.
simpl.
destruct H.
apply (le_trans _ _ _ H) in H0.
apply (le_antisymm _ _ (le_0_n p) H0).
intros.
case_eq n.
intro.
simpl.
destruct H.
rewrite H0 in H.
apply (le_antisymm _ _ (le_0_n p)) in H.
rewrite <- H.
rewrite add_0_r.
trivial.
intros.
rewrite H0 in H.
case_eq p.
intro.
simpl.
rewrite add_0_r.
trivial.
intros.
rewrite H1 in H.
destruct H.
apply le_S_n in H.
apply le_S_n in H2.
rewrite 2?sub_succ.
rewrite sub_succ_l.
rewrite <- plus_n_Sm.
rewrite IHm.
trivial.
split; assumption.
rewrite le_sub_le_add_r.
rewrite H2.
apply le_add_r.
Qed.

Lemma sub_pred_r : forall m n, n <= m -> n - 1 <= m.
Proof.
intros.
rewrite le_sub_le_add_r.
rewrite add_1_r.
apply le_S.
trivial.
Qed.

Lemma Null_Implements_Empty : forall (p : Ptr) (l :list nat), p = Null -> p implements l -> l = [].
Proof.
intros.
inversion H0.
trivial.
rewrite H in H2.
inversion H2.
Qed.

Lemma Not_Null_Implements_Non_Empty : forall (p : Ptr) (l :list nat), p <> Null -> p implements l -> l <> [].
Proof.
intros.
inversion H0.
symmetry in H1.
contradiction.
contradict H.
inversion H.
Qed.

Lemma Non_Empty_expand : forall (l : list nat), l <> [] -> exists n l', l = n :: l'.
Proof.
intros.
case_eq l; intros.
contradiction.
exists n.
exists l0.
trivial.
Qed.

Lemma Non_Empty_Implements_Not_Null : forall (p : Ptr) (l :list nat), l <> [] -> p implements l -> p <> Null.
Proof.
intros.
intro.
apply (Null_Implements_Empty _ _ H1) in H0.
contradiction.
Qed.

Lemma Next_Implements_Tail : forall (p : Ptr) (n : nat) (l l' : list nat), p implements l -> l = n :: l' -> p^.next implements l'.
Proof.
intros.
inversion H.
rewrite <- H2 in H0.
inversion H0.
simpl.
rewrite <- H3 in H0.
inversion H0.
rewrite <- H6.
assumption.
Qed.

Lemma Elem_Implements_Head : forall (p : Ptr) (n : nat) (l l' : list nat), p implements l -> l = n :: l' -> p^.elem = n.
Proof.
intros.
inversion H.
rewrite <- H2 in H0.
inversion H0.
simpl.
rewrite <- H3 in H0.
inversion H0.
trivial.
Qed.

Lemma length_tail : forall (n : nat) (l : list nat), length (n :: l) = 1 + length l.
Proof.
intros.
simpl.
trivial.
Qed.

Lemma last_tail : forall (n : nat) (l : list nat), l <> [] -> last (n :: l) = last l.
Proof.
intros.
case_eq l; intro.
contradiction.
intros.
unfold last.
simpl.
trivial.
Qed.

Lemma New_Implements_expand : forall (m n : nat) (p : Ptr) (l : list nat), m=n -> p implements l -> new m p implements (n::l).
Proof.
intros.
rewrite H.
apply imp_rec.
assumption.
Qed.
