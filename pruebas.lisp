;Documento para pruebas utilizado
;El interprete ignora los comentarios


;Expresiones algebraicas

(+ 5 10)
;15

(* (+ 1 2) 4)
;12

(+ (* (/ 9 5) 60) 32)
;140

(quote (+ 1 2))
;(+ 1 2)


;Variables

(setq x 12)
;12
(setq x (+ x 15))
;27


;Comparaciones
(setq x "AA")
(equal x "AA")
; True
(equal x "Aa")
; False
(< 2 5)
; True
(> 2 5)
; False

;List
(list 'a 'b 'c)
;(a b c)
(setq a (list 2 4 6))
(first a)
; 2 (O cualquier forma de consultar listas)
; Puede ser (first lista), (nth 0 lista) o incluso (subset lista i j)

;Atom: Esta funcion no se evaluara porque no se especifico "cons" en el proyecto
;(atom 1)
; true

;Condiciones
(setq n 9)
(cond((equal n 1) 3) (2))
;devuelve 2 debido a que es false
;Funciones

(setq n 2)

(defun cuadrado()
    (* n n)
)
(cuadrado)
;4

;Paso de parametros
(defun areacirc(rad pi)
    (* rad (* rad pi))
)
(setq resultado (areacirc 2 3.1415))
;12.5663

;Recursividad
(defun fact (n)
(cond((equal n 1) 1) ((* n (fact (- n 1)))))
)

(fact 5)
;120

(fact 6)
;720