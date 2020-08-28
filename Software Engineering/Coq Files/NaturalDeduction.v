Ltac And_Intro :=
try (do 2 red);
match goal with
  | |- _ /\ _ => split
  | _         => fail 1 "Goal is not an And-formula"
end.

Ltac Or_Intro_1 :=
try (do 2 red);
match goal with
  | |- _ \/ _ => left
  | _         => fail 1 "Goal is not an Or-formula"
end.

Ltac Or_Intro_2 :=
try (do 2 red);
match goal with
  | |- _ \/ _ => right
  | _         => fail 1 "Goal is not an Or-formula"
end.

Ltac Impl_Intro :=
try (do 2 red);
match goal with
  | |- _ -> _ => let H := fresh "H" in intro H
  | _         => fail 1 "Goal is not an Implication-formula"
end.

Ltac Not_Intro :=
try (do 2 red);
match goal with
  | |- ~ _ => let H := fresh "H" in intro H
  | _      => fail 1 "Goal is not a Not-formula"
end.

Ltac Forall_Intro :=
try (do 2 red);
match goal with
  | |- forall x, _ => let x := fresh x in intro x
  | _              => fail 1 "Goal is not a Forall-formula"
end.

Ltac Exists_Intro' t :=
try (do 2 red);
match goal with
  | |- exists x, _ => exists t
  | _              => fail 1 "Goal is not an Exists-formula"
end.

Tactic Notation "Exists_Intro" "with" constr(t) := Exists_Intro' t.

Ltac And_Elim_1' H :=
let H0 := fresh "H" in 
assert (H0 := H);
try (do 2 red in H0);
match type of H0 with
  | _ /\ _ => let H1 := fresh "H" in
              destruct H0 as [H0 H1]; clear H1
  | _      => clear H0; fail 1 "Hypothesis is not an And-formula"
end.

Tactic Notation "And_Elim_1" "in" hyp(H) := And_Elim_1' H.

Ltac And_Elim_2' H :=
let H0 := fresh "H" in 
assert (H0 := H);
try (do 2 red in H0);
match type of H0 with
  | _ /\ _ => let H1 := fresh "H" in
              destruct H0 as [H1 H0]; clear H1
  | _      => clear H0; fail 1 "Hypothesis is not an And-formula"
end.

Tactic Notation "And_Elim_2" "in" hyp(H) := And_Elim_2' H.

Ltac And_Elim_all' H :=
let H0 := fresh "H" in 
assert (H0 := H);
try (do 2 red in H0);
match type of H0 with
  | _ /\ _ => clear H; destruct H0 as [H H0]; And_Elim_all' H; And_Elim_all' H0
  | _      => clear H0
end.

Tactic Notation "And_Elim_all" "in" hyp(H) := And_Elim_all' H.

Ltac Or_Elim' H :=
try (do 2 red in H);
match type of H with
  | _ \/ _  => destruct H
  | _       => fail 1 "Hypothesis is not an Or-formula"
end.

Tactic Notation "Or_Elim" "in" hyp(H) := Or_Elim' H.

Ltac Impl_Elim' H0 H1 :=
let H0' := fresh "H" in
assert (H0' := H0);
try (do 2 red in H0');
match type of H0' with
  | ?P -> _ => 
    match type of H1 with
      | P => let H2 := fresh "H" in assert (H2 := H0' H1); clear H0'
      | _ => fail 2 "Second hypothesis does not match the assumption of the first hypothesis"
    end
  | _       => fail 1 "First hypothesis is not an Implication-formula"
end.

Tactic Notation "Impl_Elim" "in" hyp(H0) "and" hyp(H1) := Impl_Elim' H0 H1.

Ltac Not_Elim' H0 H1 :=
let H0' := fresh "H" in
assert (H0' := H0);
try (do 2 red in H0');
match type of H0' with
  | ~ ?P => 
    match type of H1 with
      | P => let H2 := fresh "H" in assert (H2 := H0' H1); clear H0'
      | _ => fail 2 "Second hypothesis does not match the body of the first hypothesis"
    end
  | _       => fail 1 "First hypothesis is not a Not-formula"
end.

Tactic Notation "Not_Elim" "in" hyp(H0) "and" hyp(H1) := Not_Elim' H0 H1.

Ltac Forall_Elim' H t :=
let H' := fresh "H" in
assert (H' := H);
try (do 2 red in H');
match type of H' with
  | forall x, _ => let H0 := fresh "H" in assert (H0 := H' t); clear H'
  | _           => fail 1 "Hypothesis is not a Forall-formula"
end.

Tactic Notation "Forall_Elim" "in" hyp(H) "with" constr(t) := Forall_Elim' H t.

Ltac Exists_Elim' H :=
let H' := fresh "H" in
assert (H' := H);
try (do 2 red in H');
match type of H with
  | exists x, _ => destruct H'
  | _           => fail 1 "Hypothesis is not an Exists-formula"
end.

Tactic Notation "Exists_Elim" "in" hyp(H) := Exists_Elim' H.

Require Import Classical.

Ltac PBC := let H := fresh "H" in apply NNPP; intro H.

Ltac assume P := cut P; [ let H := fresh "H" in intro H | idtac ].