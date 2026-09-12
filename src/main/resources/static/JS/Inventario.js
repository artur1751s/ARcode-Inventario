console.log("Inventario.js cargado✅")
//Funcion para el modal de creacion
document.addEventListener("click", (event) => {
    if (event.target.closest("#module-inventario")) {
        const botonCrear = event.target.closest("#new-product");
    
        if (botonCrear) {
            document.getElementById("modal-inventario-create").classList.add("activo");
            return;
        }
    
        if (event.target.closest("#btn-close-modal-create")) {
            cerrarModalCrear();
            return;
        }
    
        if (event.target.closest("#btn-create-cancel")) {
            cerrarModalCrear();
            return;
        }
    }
});

function cerrarModalCrear() {
    const modal = document.getElementById("modal-inventario-create");

    if (modal) {
        modal.classList.remove("activo");
    }
}

//Funcion para el modal de edicion
document.addEventListener("click", (event) => {
    if (event.target.closest("#module-inventario")) {
        const botonEditar = event.target.closest(".btn-editar");
    
        if (botonEditar) {
            const id = botonEditar.dataset.id;
            abrirModalEditar(id);
            return;
        }
    
        if (event.target.closest("#btn-close-modal")) {
            cerrarModal();
            return;
        }
    
        if (event.target.closest("#btn-cancel")) {
            cerrarModal();
            return;
        }
    }
});

document.addEventListener("submit", (event) => {
    if (event.target.id === "form-edit-inventario") {
        event.preventDefault();
        actualizarInventario(event.target);
    }
});

function abrirModalEditar(id) {
    const boton = document.querySelector(`.btn-editar[data-id="${id}"]`);
    
    if (!boton) {
        return;
    }
    
    const fila = boton.closest("tr");
    const celdas = fila.querySelectorAll("td");

    document.getElementById("product-id").value = id;

    document.getElementById("edit-product").value = celdas[1].textContent.trim();
    document.getElementById("edit-category").value = celdas[2].textContent.trim();
    document.getElementById("edit-description").value = celdas[3].textContent.trim();
    document.getElementById("edit-stock").value = celdas[4].textContent.trim();
    document.getElementById("edit-price").value = celdas[5].textContent.trim().replace(/[\$.]/g, "");
    document.getElementById("warehouse").value = boton.dataset.bodega;

    document.getElementById("modal-inventario").classList.add("activo");
}

async function actualizarInventario(form) {
    const id = document.getElementById("product-id").value;
    const datosFormulario = new FormData(form);
    const producto = Object.fromEntries(datosFormulario);

    delete producto.id_producto;

    try {
        const response = await fetch(`/api/productos/${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(producto)
            }
        );

        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }

        const productoActualizado = await response.json();

        console.log("Producto actualizado:", productoActualizado);
        cerrarModal();
        alert("Producto actualizado correctamente");
    } catch (error) {
        console.error("Error al actualizar producto:", error);
        alert("No se pudo actualizar el producto");
    }
}

function cerrarModal() {
    const modal = document.getElementById("modal-inventario");

    if (modal) {
        modal.classList.remove("activo");
    }
}