Require Import NaturalDeduction.
Require Import Hoare.
Require Import Lemmas.
Require Import Factorial.

(*
Question 1:
Prove the lemma below using the natural deduction rules of the class. 
*)

Lemma Q1 (A : Type) (P Q : A -> Prop) : (exists x, P x \/ Q x) /\ (forall x, P x -> Q x) -> (exists x, Q x).
Proof.
Qed.

(*
Question 2:
Prove Lemma Q2. The function fact is the factorial function.
*)

Lemma Q2 (n i r : nat) :
{{ True }}
  i ::= 0;;  
  r ::= 1;;
  While negb (i =? n) 
  Do
    i ::= 1 + i;;
    r ::= i * r
  Od
{{ r = fact n }}.
Proof.
Qed.

(*
Question 3:
Prove the following lemma. The lemmas f_equal, map_rev and rev_involutive might be useful.
*)

Definition factEach := map fact.

Lemma Q3 (p q r : Ptr) (l : list nat) :
{{ p implements l }}
  q ::= p;;
  r ::= Null;;
  While negb (q =p Null)
  Do
    r ::= new (fact (q^.elem)) r;;
    q ::= q^.next
  Od
{{ r implements rev (map fact l) }}.
Proof.

(* 
7.  assert(H5 := Not_Null_Implements_Non_Empty q x0 H0 H2).
8.  assert(H6 := Non_Empty_expand x0 H5).
14. apply (Next_Implements_Tail _ _ _ _ H2 H8).
16. assert (H9 := Elem_Implements_Head q x1 x0 x2 H2 H8).
18. apply (f_equal fact) in H9.
19. apply (New_Implements_expand _ _ _ _ H9 H3).
21. rewrite <- app_assoc.

7. assert (H5 := Null_Implements_Empty q x0 H0 H2).
9. rewrite app_nil_r in H4.
11. rewrite map_rev.
12. rewrite rev_involutive 
*)

Qed.


















