package es.juntadeandalucia.ingresos.sur.forms.J040091.controller;

import es.juntadeandalucia.ingresos.sur.commons.Globals;
import es.juntadeandalucia.ingresos.sur.commons.dbservices.Keuro;
import es.juntadeandalucia.ingresos.sur.commons.dbservices.Kusuari;
import es.juntadeandalucia.ingresos.sur.commons.forms.base.DefaultSurBlockController;
import es.juntadeandalucia.ingresos.sur.forms.J040091.J040091Task;
import es.juntadeandalucia.ingresos.sur.forms.J040091.model.EstadisticaAdapter;
import es.juntadeandalucia.ingresos.sur.forms.J040091.model.J040091Model;
import morphis.foundations.core.appdatalayer.events.AfterQuery;
import morphis.foundations.core.appdatalayer.events.BeforeQuery;
import morphis.foundations.core.appdatalayer.events.QueryEvent;
import morphis.foundations.core.appdatalayer.events.RowAdapterEvent;
import morphis.foundations.core.appsupportlib.Lib;
import morphis.foundations.core.appsupportlib.runtime.BlockServices;
import morphis.foundations.core.appsupportlib.runtime.ItemServices;
import morphis.foundations.core.appsupportlib.runtime.TaskServices;
import morphis.foundations.core.appsupportlib.runtime.ViewServices;
import morphis.foundations.core.appsupportlib.runtime.action.ActionTrigger;
import morphis.foundations.core.appsupportlib.runtime.control.IFormController;
import morphis.foundations.core.appsupportlib.ui.KeyFunction;
import morphis.foundations.core.types.NBool;

import morphis.foundations.core.types.Types;

public class EstadisticaController extends DefaultSurBlockController {

	public EstadisticaController(IFormController parentController, String name) {
		super(parentController, name);
	}

	@Override
	public J040091Task getTask() {
		return (J040091Task) super.getTask();
	}

	public J040091Model getFormModel() {
		return this.getTask().getModel();
	}

	//action methods generated from triggers
	/* Original PL/SQL code code for TRIGGER ESTADISTICA.PRE-QUERY
	 IF :ESTADISTICA.EJERCICIO IS NULL OR
	LENGTH(:ESTADISTICA.EJERCICIO)<>4 THEN
	Mostrar_mensaje('SUR-01379','E',TRUE); --Teclee ejercicio con 4 dígitos
	END IF;
	
	:CG$CTRL.EJERCICIO:=:ESTADISTICA.EJERCICIO;
	
	
	SET_BLOCK_PROPERTY('ESTADISTICA',QUERY_DATA_SOURCE_NAME,'(SELECT '''||:CG$CTRL.EJERCICIO||''' EJERCICIO FROM DUAL)');
	
	<multilinecomment>SET_BLOCK_PROPERTY('ESTADISTICA',QUERY_DATA_SOURCE_NAME,'(SELECT '''||:CG$CTRL.EJERCICIO||''' EJERCICIO , NOMPROVI PROVINCIA
	                                                  FROM GLOBAL_NAME G, SU_PROVIN P
	                                                  WHERE SUBSTR(G.GLOBAL_NAME,7,2)=P.CODPROVI)');
	</multilinecomment>
	
	:CG$CTRL.VER_MAQUINA       := :ESTADISTICA.VER_MAQUINA;
	:CG$CTRL.VER_BINGO         := :ESTADISTICA.VER_BINGO;
	:CG$CTRL.VER_CASINO        := :ESTADISTICA.VER_CASINO;
	:CG$CTRL.VER_LIQUIDACIONES := :ESTADISTICA.VER_LIQUIDACIONES;
	:CG$CTRL.VER_LIQ_BINGO     := :ESTADISTICA.VER_LIQ_BINGO;
	:CG$CTRL.VER_LIQ_CASINO    := :ESTADISTICA.VER_LIQ_CASINO;
	:CG$CTRL.VER_OTROS         := :ESTADISTICA.VER_OTROS;
	
	
	--SE ACEPTAN DIFERENCIAS DE 2 PESETAS/CENTIMOS A PARA ACEPTAR LOS DESCUADRES DE LA CONVERSION
	IF KEURO.FMONEDA='P' THEN
	IF :ESTADISTICA.EJERCICIO<='2001' THEN
	:CONVERSION.VALOR_300:=300;
	ELSE
	:CONVERSION.VALOR_300:=333;
	END IF;
	:CONVERSION.VALOR_500:=500;
	:CONVERSION.VALOR_1000:=1000;
	
	ELSIF KEURO.FMONEDA='E' THEN
	IF :ESTADISTICA.EJERCICIO<='2001' THEN
	:CONVERSION.VALOR_300:=1.8;
	ELSE
	:CONVERSION.VALOR_300:=2;
	END IF;
	:CONVERSION.VALOR_500:=3;
	:CONVERSION.VALOR_1000:=6;
	END IF;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * ESTADISTICA.PRE-QUERY
	 *
	 *
	 *</p>
	 * @param args
	*/

	@BeforeQuery
	public void estadistica_BeforeQuery(QueryEvent args) {

		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		if (estadisticaElement.getEjercicio().isNull() || Lib.length(estadisticaElement.getEjercicio()).notEquals(4)) {
			this.getTask().getServices().mostrarMensaje(Types.toStr("SUR-01379"), Types.toStr("E"), Types.toBool(NBool.True));
		}
		this.getFormModel().getCgCtrl().setEjercicio(estadisticaElement.getEjercicio());
		BlockServices.setBlockQueryDataSourceName("ESTADISTICA", Types.toStr("(SELECT '").append(this.getFormModel().getCgCtrl().getEjercicio()).append("' EJERCICIO FROM DUAL)"));
		// SET_BLOCK_PROPERTY('ESTADISTICA',QUERY_DATA_SOURCE_NAME,'(SELECT '''||:CG$CTRL.EJERCICIO||''' EJERCICIO , NOMPROVI PROVINCIA
		// FROM GLOBAL_NAME G, SU_PROVIN P
		// WHERE SUBSTR(G.GLOBAL_NAME,7,2)=P.CODPROVI)');
		this.getFormModel().getCgCtrl().setVerMaquina(estadisticaElement.getVerMaquina());
		this.getFormModel().getCgCtrl().setVerBingo(estadisticaElement.getVerBingo());
		this.getFormModel().getCgCtrl().setVerCasino(estadisticaElement.getVerCasino());
		this.getFormModel().getCgCtrl().setVerLiquidaciones(estadisticaElement.getVerLiquidaciones());
		this.getFormModel().getCgCtrl().setVerLiqBingo(estadisticaElement.getVerLiqBingo());
		this.getFormModel().getCgCtrl().setVerLiqCasino(estadisticaElement.getVerLiqCasino());
		this.getFormModel().getCgCtrl().setVerOtros(estadisticaElement.getVerOtros());
		// SE ACEPTAN DIFERENCIAS DE 2 PESETAS/CENTIMOS A PARA ACEPTAR LOS DESCUADRES DE LA CONVERSION
		if (Keuro.fmoneda().equals("P")) {
			if (estadisticaElement.getEjercicio().lesserOrEquals("2001")) {
				this.getFormModel().getConversion().setValor300(Types.toNumber(300));
			} else {
				this.getFormModel().getConversion().setValor300(Types.toNumber(333));
			}
			this.getFormModel().getConversion().setValor500(Types.toNumber(500));
			this.getFormModel().getConversion().setValor1000(Types.toNumber(1000));
		} else if (Keuro.fmoneda().equals("E")) {
			if (estadisticaElement.getEjercicio().lesserOrEquals("2001")) {
				this.getFormModel().getConversion().setValor300(Types.toNumber(1.8));
			} else {
				this.getFormModel().getConversion().setValor300(Types.toNumber(2));
			}
			this.getFormModel().getConversion().setValor500(Types.toNumber(3));
			this.getFormModel().getConversion().setValor1000(Types.toNumber(6));
		}
	}

	/* Original PL/SQL code code for TRIGGER ESTADISTICA.POST-QUERY
	 :ESTADISTICA.VER_MAQUINA       := :CG$CTRL.VER_MAQUINA;
	:ESTADISTICA.VER_BINGO         := :CG$CTRL.VER_BINGO;
	:ESTADISTICA.VER_CASINO        := :CG$CTRL.VER_CASINO;
	:ESTADISTICA.VER_LIQUIDACIONES := :CG$CTRL.VER_LIQUIDACIONES;
	:ESTADISTICA.VER_LIQ_BINGO     := :CG$CTRL.VER_LIQ_BINGO;
	:ESTADISTICA.VER_LIQ_CASINO    := :CG$CTRL.VER_LIQ_CASINO;
	:ESTADISTICA.VER_OTROS         := :CG$CTRL.VER_OTROS;
	
	:ESTADISTICA.RB_LIQ_PERIODO     := 'T';
	:ESTADISTICA.RB_LIQ_CAS_PERIODO := 'T';
	:ESTADISTICA.RB_LIQ_BIN_PERIODO := 'T';
	
	:ESTADISTICA.CODPROVI := :CG$CTRL.CODPROVI;
	:ESTADISTICA.NOMPROVI := :CG$CTRL.NOMPROVI;
	
	CALCULA_ESTADISTICA;
	
	
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * ESTADISTICA.POST-QUERY
	 *
	 *
	 *</p>
	 * @param args
	*/

	@AfterQuery
	public void estadistica_AfterQuery(RowAdapterEvent args) {

		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) args.getRow();

		// F2J_WARNING : Post-query code is executed once for every row retrieved. If you expect the query to return many records, this may cause a performance problem.
		estadisticaElement.setVerMaquina(this.getFormModel().getCgCtrl().getVerMaquina());
		estadisticaElement.setVerBingo(this.getFormModel().getCgCtrl().getVerBingo());
		estadisticaElement.setVerCasino(this.getFormModel().getCgCtrl().getVerCasino());
		estadisticaElement.setVerLiquidaciones(this.getFormModel().getCgCtrl().getVerLiquidaciones());
		estadisticaElement.setVerLiqBingo(this.getFormModel().getCgCtrl().getVerLiqBingo());
		estadisticaElement.setVerLiqCasino(this.getFormModel().getCgCtrl().getVerLiqCasino());
		estadisticaElement.setVerOtros(this.getFormModel().getCgCtrl().getVerOtros());
		estadisticaElement.setRbLiqPeriodo(Types.toStr("T"));
		estadisticaElement.setRbLiqCasPeriodo(Types.toStr("T"));
		estadisticaElement.setRbLiqBinPeriodo(Types.toStr("T"));
		estadisticaElement.setCodprovi(this.getFormModel().getCgCtrl().getCodprovi());
		estadisticaElement.setNomprovi(this.getFormModel().getCgCtrl().getNomprovi());
		this.getTask().getServices().calculaEstadistica(estadisticaElement);
	}

	/* Original PL/SQL code code for TRIGGER CODPROVI.WHEN-NEW-ITEM-INSTANCE
	 GO_ITEM('ESTADISTICA.BT_PROVINCIA');
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * CODPROVI.WHEN-NEW-ITEM-INSTANCE
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "WHEN-NEW-ITEM-INSTANCE", item = "CODPROVI", function = KeyFunction.ITEM_CHANGE)
	public void codprovi_itemChange() {

		ItemServices.goItem(Types.toStr("ESTADISTICA.BT_PROVINCIA"));
	}

	/* Original PL/SQL code code for TRIGGER NOMPROVI.WHEN-NEW-ITEM-INSTANCE
	 GO_ITEM('ESTADISTICA.BT_PROVINCIA');
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * NOMPROVI.WHEN-NEW-ITEM-INSTANCE
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "WHEN-NEW-ITEM-INSTANCE", item = "NOMPROVI", function = KeyFunction.ITEM_CHANGE)
	public void nomprovi_itemChange() {

		ItemServices.goItem(Types.toStr("ESTADISTICA.BT_PROVINCIA"));
	}

	/* Original PL/SQL code code for TRIGGER BT_PROVINCIA.WHEN-BUTTON-PRESSED
	 DECLARE
	
	vElegido BOOLEAN := FALSE;  --Se ha seleccionado un elemento
	
	BEGIN
	
	IF KUSUARI.FSERVERGE(USER)='__' THEN
	--Sólo mostraremos la LOV si estamos en modo ENTER-QUERY
	IF :SYSTEM.MODE = 'ENTER-QUERY' OR
	:SYSTEM.RECORD_STATUS IN ('NEW','INSERT') THEN
	
	
	vElegido := SHOW_LOV('LV_PROVINCIA');
	
	--Si no elegimos ninguna D.P., dejamos en blanco
	--su código y su descripción
	IF NOT vElegido THEN
	<multilinecomment>      :CG$CTRL.CODPROVI := NULL;
	:CG$CTRL.NOMPROVI := NULL;
	:ESTADISTICA.CODPROVI := NULL;
	:ESTADISTICA.NOMPROVI := NULL;</multilinecomment>
	NULL;
	
	ELSE
	:CG$CTRL.CODPROVI:=:ESTADISTICA.CODPROVI;
	:CG$CTRL.NOMPROVI:=:ESTADISTICA.NOMPROVI;
	
	END IF;
	
	
	ELSE
	
	NULL;
	
	END IF;
	ELSE
	
	MOSTRAR_MENSAJE('SUR-01334#1. Selección no activa para el usuario','I',FALSE);
	END IF;
	
	END;
	
	<multilinecomment>**** Fin Creado por SUDEJGN0 el 17/04/2000 ****</multilinecomment>
	
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BT_PROVINCIA.WHEN-BUTTON-PRESSED
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "WHEN-BUTTON-PRESSED", item = "BT_PROVINCIA")
	public void btProvincia_buttonClick() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		{
			NBool velegido = Types.toBool(NBool.False);
			if (Kusuari.fserverge(TaskServices.getUser()).equals("__")) {
				// Sólo mostraremos la LOV si estamos en modo ENTER-QUERY
				// F2J_WARNING : The built-in  call was converted but there can be semantic differences in the resulting code.
				if (TaskServices.getMode().equals("ENTER-QUERY") || (TaskServices.getRecordStatus().equals("NEW") || TaskServices.getRecordStatus().equals("INSERT"))) {
					velegido = TaskServices.showLov("LV_PROVINCIA");
					// Si no elegimos ninguna D.P., dejamos en blanco
					// su código y su descripción
					if (velegido.not()) {
						//       :CG$CTRL.CODPROVI := NULL;
						// :CG$CTRL.NOMPROVI := NULL;
						// :ESTADISTICA.CODPROVI := NULL;
						// :ESTADISTICA.NOMPROVI := NULL;
					} else {
						this.getFormModel().getCgCtrl().setCodprovi(estadisticaElement.getCodprovi());
						this.getFormModel().getCgCtrl().setNomprovi(estadisticaElement.getNomprovi());
					}
				} else {
				}
			} else {
				this.getTask().getServices().mostrarMensaje(Types.toStr("SUR-01334#1. Selección no activa para el usuario"), Types.toStr("I"), Types.toBool(NBool.False));
			}
		}
	}

	/* Original PL/SQL code code for TRIGGER VER_MAQUINA.WHEN-CHECKBOX-CHANGED
	 COMPRUEBA_INDICADORES('MAQUINAS');
	
	IF :SYSTEM.MODE='NORMAL' THEN
	IF :ESTADISTICA.VER_MAQUINA='S' THEN
	--SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE REALIZA LA ESTADISTICA DE
	--MAQUINAS
	SET_TAB_PAGE_PROPERTY('MAQUINAS',VISIBLE,PROPERTY_TRUE);
	CALCULA_ESTADISTICA_MAQUINAS;
	ELSE
	--SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE BORRA TODO LO DE MAQUINAS
	--SE PIDE CONFIRMACION
	MOSTRAR_MENSAJE('SUR-02000 #1#2¿Desea borrar la estadística de máquinas?','D',FALSE);
	IF NVL(:GLOBAL.QMS$DIALOG_ANSWER,'N')='Y' THEN
	SET_TAB_PAGE_PROPERTY('MAQUINAS',VISIBLE,PROPERTY_FALSE);
	
	:DOCST1:=NULL;
	:DOCST2:=NULL;
	:DOCST3:=NULL;
	:DOCST4:=NULL;
	--      :DOCSTTOTAL:=NULL;
	:TASA1:=NULL;
	:TASA2:=NULL;
	:TASA3:=NULL;
	:TASA4:=NULL;
	--      :TASATOTAL:=NULL;
	:DOCSR1:=NULL;
	:DOCSR2:=NULL;
	:DOCSR3:=NULL;
	:DOCSR4:=NULL;
	--      :DOCSRTOTAL:=NULL;
	:RECARGO1:=NULL;
	:RECARGO2:=NULL;
	:RECARGO3:=NULL;
	:RECARGO4:=NULL;
	--      :RECARGOTOTAL:=NULL;
	:ESTADISTICA.CENSO_FISCAL:=NULL;
	:ESTADISTICA.CENSO_ADMINISTRATIVO:=NULL;
	SET_ITEM_PROPERTY('ESTADISTICA.CENSO_ADMINISTRATIVO',PROMPT_TEXT,'Censo admin.');
	
	ELSE
	:ESTADISTICA.VER_MAQUINA:='S';
	END IF;
	END IF;
	
	ELSE --OTRO MODO
	
	IF :ESTADISTICA.VER_MAQUINA='S' THEN
	SET_TAB_PAGE_PROPERTY('MAQUINAS',VISIBLE,PROPERTY_TRUE);
	ELSE
	SET_TAB_PAGE_PROPERTY('MAQUINAS',VISIBLE,PROPERTY_FALSE);
	END IF;
	END IF;
	
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * VER_MAQUINA.WHEN-CHECKBOX-CHANGED
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "WHEN-CHECKBOX-CHANGED", item = "VER_MAQUINA")
	public void verMaquina_checkBoxChange() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		this.getTask().getServices().compruebaIndicadores(estadisticaElement, Types.toStr("MAQUINAS"));
		if (TaskServices.getMode().equals("NORMAL")) {
			if (estadisticaElement.getVerMaquina().equals("S")) {
				// SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE REALIZA LA ESTADISTICA DE
				// MAQUINAS
				ViewServices.setTabPageVisible("MAQUINAS", true);
				this.getTask().getServices().calculaEstadisticaMaquinas(estadisticaElement);
			} else {
				// SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE BORRA TODO LO DE MAQUINAS
				// SE PIDE CONFIRMACION
				this.getTask().getServices().mostrarMensaje(Types.toStr("SUR-02000 #1#2¿Desea borrar la estadística de máquinas?"), Types.toStr("D"), Types.toBool(NBool.False));
				if (Lib.isNull(Globals.getGlobal("QMS$DIALOG_ANSWER"), "N").equals("Y")) {
					ViewServices.setTabPageVisible("MAQUINAS", false);
					estadisticaElement.setDocst1(Types.toNumber(null));
					estadisticaElement.setDocst2(Types.toNumber(null));
					estadisticaElement.setDocst3(Types.toNumber(null));
					estadisticaElement.setDocst4(Types.toNumber(null));
					//       :DOCSTTOTAL:=NULL;
					estadisticaElement.setTasa1(Types.toNumber(null));
					estadisticaElement.setTasa2(Types.toNumber(null));
					estadisticaElement.setTasa3(Types.toNumber(null));
					estadisticaElement.setTasa4(Types.toNumber(null));
					//       :TASATOTAL:=NULL;
					estadisticaElement.setDocsr1(Types.toNumber(null));
					estadisticaElement.setDocsr2(Types.toNumber(null));
					estadisticaElement.setDocsr3(Types.toNumber(null));
					estadisticaElement.setDocsr4(Types.toNumber(null));
					//       :DOCSRTOTAL:=NULL;
					estadisticaElement.setRecargo1(Types.toNumber(null));
					estadisticaElement.setRecargo2(Types.toNumber(null));
					estadisticaElement.setRecargo3(Types.toNumber(null));
					estadisticaElement.setRecargo4(Types.toNumber(null));
					//       :RECARGOTOTAL:=NULL;
					estadisticaElement.setCensoFiscal(Types.toNumber(null));
					estadisticaElement.setCensoAdministrativo(Types.toNumber(null));
					ItemServices.setItemPromptText("ESTADISTICA.CENSO_ADMINISTRATIVO", "Censo admin.");
				} else {
					estadisticaElement.setVerMaquina(Types.toStr("S"));
				}
			}
		} else {
			// OTRO MODO
			if (estadisticaElement.getVerMaquina().equals("S")) {
				ViewServices.setTabPageVisible("MAQUINAS", true);
			} else {
				ViewServices.setTabPageVisible("MAQUINAS", false);
			}
		}
	}

	/* Original PL/SQL code code for TRIGGER VER_LIQUIDACIONES.WHEN-CHECKBOX-CHANGED
	 COMPRUEBA_INDICADORES('LIQUIDACIONES');
	
	IF :SYSTEM.MODE='NORMAL' THEN
	IF :ESTADISTICA.VER_LIQUIDACIONES='S' THEN
	--SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE REALIZA LA ESTADISTICA DE
	--LIQUIDACIONES
	SET_TAB_PAGE_PROPERTY('LIQUIDACIONES',VISIBLE,PROPERTY_TRUE);
	CALCULA_ESTADISTICA_LIQUID;
	ELSE
	--SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE BORRA TODO LO DE LIQUIDACIONES
	--SE PIDE CONFIRMACION
	MOSTRAR_MENSAJE('SUR-02000 #1#2¿Desea borrar la estadística de liquidaciones de máquinas?','D',FALSE);
	IF NVL(:GLOBAL.QMS$DIALOG_ANSWER,'N')='Y' THEN
	SET_TAB_PAGE_PROPERTY('LIQUIDACIONES',VISIBLE,PROPERTY_FALSE);
	
	:ESTADISTICA.LIQUID_G1_DOCT1:=NULL;
	:ESTADISTICA.LIQUID_G1_T1:=NULL;
	:ESTADISTICA.LIQUID_G1_DOCR1:=NULL;
	:ESTADISTICA.LIQUID_G1_R1:=NULL;
	:ESTADISTICA.LIQUID_G2_DOCT1:=NULL;
	:ESTADISTICA.LIQUID_G2_T1:=NULL;
	:ESTADISTICA.LIQUID_G2_DOCR1:=NULL;
	:ESTADISTICA.LIQUID_G2_R1:=NULL;
	:ESTADISTICA.LIQUID_G3_DOCT1:=NULL;
	:ESTADISTICA.LIQUID_G3_T1:=NULL;
	:ESTADISTICA.LIQUID_G3_DOCR1:=NULL;
	:ESTADISTICA.LIQUID_G3_R1:=NULL;
	:ESTADISTICA.LIQUID_G4_DOCT1:=NULL;
	:ESTADISTICA.LIQUID_G4_T1:=NULL;
	:ESTADISTICA.LIQUID_G4_DOCR1:=NULL;
	:ESTADISTICA.LIQUID_G4_R1:=NULL;
	:ESTADISTICA.LIQUID_G5_DOCT1:=NULL;
	:ESTADISTICA.LIQUID_G5_T1:=NULL;
	:ESTADISTICA.LIQUID_G5_DOCR1:=NULL;
	:ESTADISTICA.LIQUID_G5_R1:=NULL;
	:ESTADISTICA.LIQUID_G6_DOCT1:=NULL;
	:ESTADISTICA.LIQUID_G6_T1:=NULL;
	:ESTADISTICA.LIQUID_G6_DOCR1:=NULL;
	:ESTADISTICA.LIQUID_G6_R1:=NULL;
	:ESTADISTICA.LIQUID_RESTO_DOCT1:=NULL;
	:ESTADISTICA.LIQUID_RESTO_T1:=NULL;
	:ESTADISTICA.LIQUID_RESTO_DOCR1:=NULL;
	:ESTADISTICA.LIQUID_RESTO_R1:=NULL;
	
	:ESTADISTICA.LIQUID_G1_DOCT2:=NULL;
	:ESTADISTICA.LIQUID_G1_T2:=NULL;
	:ESTADISTICA.LIQUID_G1_DOCR2:=NULL;
	:ESTADISTICA.LIQUID_G1_R2:=NULL;
	:ESTADISTICA.LIQUID_G2_DOCT2:=NULL;
	:ESTADISTICA.LIQUID_G2_T2:=NULL;
	:ESTADISTICA.LIQUID_G2_DOCR2:=NULL;
	:ESTADISTICA.LIQUID_G2_R2:=NULL;
	:ESTADISTICA.LIQUID_G3_DOCT2:=NULL;
	:ESTADISTICA.LIQUID_G3_T2:=NULL;
	:ESTADISTICA.LIQUID_G3_DOCR2:=NULL;
	:ESTADISTICA.LIQUID_G3_R2:=NULL;
	:ESTADISTICA.LIQUID_G4_DOCT2:=NULL;
	:ESTADISTICA.LIQUID_G4_T2:=NULL;
	:ESTADISTICA.LIQUID_G4_DOCR2:=NULL;
	:ESTADISTICA.LIQUID_G4_R2:=NULL;
	:ESTADISTICA.LIQUID_G5_DOCT2:=NULL;
	:ESTADISTICA.LIQUID_G5_T2:=NULL;
	:ESTADISTICA.LIQUID_G5_DOCR2:=NULL;
	:ESTADISTICA.LIQUID_G5_R2:=NULL;
	:ESTADISTICA.LIQUID_G6_DOCT2:=NULL;
	:ESTADISTICA.LIQUID_G6_T2:=NULL;
	:ESTADISTICA.LIQUID_G6_DOCR2:=NULL;
	:ESTADISTICA.LIQUID_G6_R2:=NULL;
	:ESTADISTICA.LIQUID_RESTO_DOCT2:=NULL;
	:ESTADISTICA.LIQUID_RESTO_T2:=NULL;
	:ESTADISTICA.LIQUID_RESTO_DOCR2:=NULL;
	:ESTADISTICA.LIQUID_RESTO_R2:=NULL;
	
	:ESTADISTICA.LIQUID_G1_DOCT3:=NULL;
	:ESTADISTICA.LIQUID_G1_T3:=NULL;
	:ESTADISTICA.LIQUID_G1_DOCR3:=NULL;
	:ESTADISTICA.LIQUID_G1_R3:=NULL;
	:ESTADISTICA.LIQUID_G2_DOCT3:=NULL;
	:ESTADISTICA.LIQUID_G2_T3:=NULL;
	:ESTADISTICA.LIQUID_G2_DOCR3:=NULL;
	:ESTADISTICA.LIQUID_G2_R3:=NULL;
	:ESTADISTICA.LIQUID_G3_DOCT3:=NULL;
	:ESTADISTICA.LIQUID_G3_T3:=NULL;
	:ESTADISTICA.LIQUID_G3_DOCR3:=NULL;
	:ESTADISTICA.LIQUID_G3_R3:=NULL;
	:ESTADISTICA.LIQUID_G4_DOCT3:=NULL;
	:ESTADISTICA.LIQUID_G4_T3:=NULL;
	:ESTADISTICA.LIQUID_G4_DOCR3:=NULL;
	:ESTADISTICA.LIQUID_G4_R3:=NULL;
	:ESTADISTICA.LIQUID_G5_DOCT3:=NULL;
	:ESTADISTICA.LIQUID_G5_T3:=NULL;
	:ESTADISTICA.LIQUID_G5_DOCR3:=NULL;
	:ESTADISTICA.LIQUID_G5_R3:=NULL;
	:ESTADISTICA.LIQUID_G6_DOCT3:=NULL;
	:ESTADISTICA.LIQUID_G6_T3:=NULL;
	:ESTADISTICA.LIQUID_G6_DOCR3:=NULL;
	:ESTADISTICA.LIQUID_G6_R3:=NULL;
	:ESTADISTICA.LIQUID_RESTO_DOCT3:=NULL;
	:ESTADISTICA.LIQUID_RESTO_T3:=NULL;
	:ESTADISTICA.LIQUID_RESTO_DOCR3:=NULL;
	:ESTADISTICA.LIQUID_RESTO_R3:=NULL;
	
	:ESTADISTICA.LIQUID_G1_DOCT4:=NULL;
	:ESTADISTICA.LIQUID_G1_T4:=NULL;
	:ESTADISTICA.LIQUID_G1_DOCR4:=NULL;
	:ESTADISTICA.LIQUID_G1_R4:=NULL;
	:ESTADISTICA.LIQUID_G2_DOCT4:=NULL;
	:ESTADISTICA.LIQUID_G2_T4:=NULL;
	:ESTADISTICA.LIQUID_G2_DOCR4:=NULL;
	:ESTADISTICA.LIQUID_G2_R4:=NULL;
	:ESTADISTICA.LIQUID_G3_DOCT4:=NULL;
	:ESTADISTICA.LIQUID_G3_T4:=NULL;
	:ESTADISTICA.LIQUID_G3_DOCR4:=NULL;
	:ESTADISTICA.LIQUID_G3_R4:=NULL;
	:ESTADISTICA.LIQUID_G4_DOCT4:=NULL;
	:ESTADISTICA.LIQUID_G4_T4:=NULL;
	:ESTADISTICA.LIQUID_G4_DOCR4:=NULL;
	:ESTADISTICA.LIQUID_G4_R4:=NULL;
	:ESTADISTICA.LIQUID_G5_DOCT4:=NULL;
	:ESTADISTICA.LIQUID_G5_T4:=NULL;
	:ESTADISTICA.LIQUID_G5_DOCR4:=NULL;
	:ESTADISTICA.LIQUID_G5_R4:=NULL;
	:ESTADISTICA.LIQUID_G6_DOCT4:=NULL;
	:ESTADISTICA.LIQUID_G6_T4:=NULL;
	:ESTADISTICA.LIQUID_G6_DOCR4:=NULL;
	:ESTADISTICA.LIQUID_G6_R4:=NULL;
	:ESTADISTICA.LIQUID_RESTO_DOCT4:=NULL;
	:ESTADISTICA.LIQUID_RESTO_T4:=NULL;
	:ESTADISTICA.LIQUID_RESTO_DOCR4:=NULL;
	:ESTADISTICA.LIQUID_RESTO_R4:=NULL;
	
	
	
	
	ELSE
	:ESTADISTICA.VER_LIQUIDACIONES:='S';
	END IF;
	END IF;
	
	ELSE --OTRO MODO
	
	IF :ESTADISTICA.VER_LIQUIDACIONES='S' THEN
	SET_TAB_PAGE_PROPERTY('LIQUIDACIONES',VISIBLE,PROPERTY_TRUE);
	ELSE
	SET_TAB_PAGE_PROPERTY('LIQUIDACIONES',VISIBLE,PROPERTY_FALSE);
	END IF;
	END IF;
	
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * VER_LIQUIDACIONES.WHEN-CHECKBOX-CHANGED
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "WHEN-CHECKBOX-CHANGED", item = "VER_LIQUIDACIONES")
	public void verLiquidaciones_checkBoxChange() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		this.getTask().getServices().compruebaIndicadores(estadisticaElement, Types.toStr("LIQUIDACIONES"));
		if (TaskServices.getMode().equals("NORMAL")) {
			if (estadisticaElement.getVerLiquidaciones().equals("S")) {
				// SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE REALIZA LA ESTADISTICA DE
				// LIQUIDACIONES
				ViewServices.setTabPageVisible("LIQUIDACIONES", true);
				this.getTask().getServices().calculaEstadisticaLiquid(estadisticaElement);
			} else {
				// SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE BORRA TODO LO DE LIQUIDACIONES
				// SE PIDE CONFIRMACION
				this.getTask().getServices().mostrarMensaje(Types.toStr("SUR-02000 #1#2¿Desea borrar la estadística de liquidaciones de máquinas?"), Types.toStr("D"), Types.toBool(NBool.False));
				if (Lib.isNull(Globals.getGlobal("QMS$DIALOG_ANSWER"), "N").equals("Y")) {
					ViewServices.setTabPageVisible("LIQUIDACIONES", false);
					estadisticaElement.setLiquidG1Doct1(Types.toNumber(null));
					estadisticaElement.setLiquidG1T1(Types.toNumber(null));
					estadisticaElement.setLiquidG1Docr1(Types.toNumber(null));
					estadisticaElement.setLiquidG1R1(Types.toNumber(null));
					estadisticaElement.setLiquidG2Doct1(Types.toNumber(null));
					estadisticaElement.setLiquidG2T1(Types.toNumber(null));
					estadisticaElement.setLiquidG2Docr1(Types.toNumber(null));
					estadisticaElement.setLiquidG2R1(Types.toNumber(null));
					estadisticaElement.setLiquidG3Doct1(Types.toNumber(null));
					estadisticaElement.setLiquidG3T1(Types.toNumber(null));
					estadisticaElement.setLiquidG3Docr1(Types.toNumber(null));
					estadisticaElement.setLiquidG3R1(Types.toNumber(null));
					estadisticaElement.setLiquidG4Doct1(Types.toNumber(null));
					estadisticaElement.setLiquidG4T1(Types.toNumber(null));
					estadisticaElement.setLiquidG4Docr1(Types.toNumber(null));
					estadisticaElement.setLiquidG4R1(Types.toNumber(null));
					estadisticaElement.setLiquidG5Doct1(Types.toNumber(null));
					estadisticaElement.setLiquidG5T1(Types.toNumber(null));
					estadisticaElement.setLiquidG5Docr1(Types.toNumber(null));
					estadisticaElement.setLiquidG5R1(Types.toNumber(null));
					estadisticaElement.setLiquidG6Doct1(Types.toNumber(null));
					estadisticaElement.setLiquidG6T1(Types.toNumber(null));
					estadisticaElement.setLiquidG6Docr1(Types.toNumber(null));
					estadisticaElement.setLiquidG6R1(Types.toNumber(null));
					estadisticaElement.setLiquidRestoDoct1(Types.toNumber(null));
					estadisticaElement.setLiquidRestoT1(Types.toNumber(null));
					estadisticaElement.setLiquidRestoDocr1(Types.toNumber(null));
					estadisticaElement.setLiquidRestoR1(Types.toNumber(null));
					estadisticaElement.setLiquidG1Doct2(Types.toNumber(null));
					estadisticaElement.setLiquidG1T2(Types.toNumber(null));
					estadisticaElement.setLiquidG1Docr2(Types.toNumber(null));
					estadisticaElement.setLiquidG1R2(Types.toNumber(null));
					estadisticaElement.setLiquidG2Doct2(Types.toNumber(null));
					estadisticaElement.setLiquidG2T2(Types.toNumber(null));
					estadisticaElement.setLiquidG2Docr2(Types.toNumber(null));
					estadisticaElement.setLiquidG2R2(Types.toNumber(null));
					estadisticaElement.setLiquidG3Doct2(Types.toNumber(null));
					estadisticaElement.setLiquidG3T2(Types.toNumber(null));
					estadisticaElement.setLiquidG3Docr2(Types.toNumber(null));
					estadisticaElement.setLiquidG3R2(Types.toNumber(null));
					estadisticaElement.setLiquidG4Doct2(Types.toNumber(null));
					estadisticaElement.setLiquidG4T2(Types.toNumber(null));
					estadisticaElement.setLiquidG4Docr2(Types.toNumber(null));
					estadisticaElement.setLiquidG4R2(Types.toNumber(null));
					estadisticaElement.setLiquidG5Doct2(Types.toNumber(null));
					estadisticaElement.setLiquidG5T2(Types.toNumber(null));
					estadisticaElement.setLiquidG5Docr2(Types.toNumber(null));
					estadisticaElement.setLiquidG5R2(Types.toNumber(null));
					estadisticaElement.setLiquidG6Doct2(Types.toNumber(null));
					estadisticaElement.setLiquidG6T2(Types.toNumber(null));
					estadisticaElement.setLiquidG6Docr2(Types.toNumber(null));
					estadisticaElement.setLiquidG6R2(Types.toNumber(null));
					estadisticaElement.setLiquidRestoDoct2(Types.toNumber(null));
					estadisticaElement.setLiquidRestoT2(Types.toNumber(null));
					estadisticaElement.setLiquidRestoDocr2(Types.toNumber(null));
					estadisticaElement.setLiquidRestoR2(Types.toNumber(null));
					estadisticaElement.setLiquidG1Doct3(Types.toNumber(null));
					estadisticaElement.setLiquidG1T3(Types.toNumber(null));
					estadisticaElement.setLiquidG1Docr3(Types.toNumber(null));
					estadisticaElement.setLiquidG1R3(Types.toNumber(null));
					estadisticaElement.setLiquidG2Doct3(Types.toNumber(null));
					estadisticaElement.setLiquidG2T3(Types.toNumber(null));
					estadisticaElement.setLiquidG2Docr3(Types.toNumber(null));
					estadisticaElement.setLiquidG2R3(Types.toNumber(null));
					estadisticaElement.setLiquidG3Doct3(Types.toNumber(null));
					estadisticaElement.setLiquidG3T3(Types.toNumber(null));
					estadisticaElement.setLiquidG3Docr3(Types.toNumber(null));
					estadisticaElement.setLiquidG3R3(Types.toNumber(null));
					estadisticaElement.setLiquidG4Doct3(Types.toNumber(null));
					estadisticaElement.setLiquidG4T3(Types.toNumber(null));
					estadisticaElement.setLiquidG4Docr3(Types.toNumber(null));
					estadisticaElement.setLiquidG4R3(Types.toNumber(null));
					estadisticaElement.setLiquidG5Doct3(Types.toNumber(null));
					estadisticaElement.setLiquidG5T3(Types.toNumber(null));
					estadisticaElement.setLiquidG5Docr3(Types.toNumber(null));
					estadisticaElement.setLiquidG5R3(Types.toNumber(null));
					estadisticaElement.setLiquidG6Doct3(Types.toNumber(null));
					estadisticaElement.setLiquidG6T3(Types.toNumber(null));
					estadisticaElement.setLiquidG6Docr3(Types.toNumber(null));
					estadisticaElement.setLiquidG6R3(Types.toNumber(null));
					estadisticaElement.setLiquidRestoDoct3(Types.toNumber(null));
					estadisticaElement.setLiquidRestoT3(Types.toNumber(null));
					estadisticaElement.setLiquidRestoDocr3(Types.toNumber(null));
					estadisticaElement.setLiquidRestoR3(Types.toNumber(null));
					estadisticaElement.setLiquidG1Doct4(Types.toNumber(null));
					estadisticaElement.setLiquidG1T4(Types.toNumber(null));
					estadisticaElement.setLiquidG1Docr4(Types.toNumber(null));
					estadisticaElement.setLiquidG1R4(Types.toNumber(null));
					estadisticaElement.setLiquidG2Doct4(Types.toNumber(null));
					estadisticaElement.setLiquidG2T4(Types.toNumber(null));
					estadisticaElement.setLiquidG2Docr4(Types.toNumber(null));
					estadisticaElement.setLiquidG2R4(Types.toNumber(null));
					estadisticaElement.setLiquidG3Doct4(Types.toNumber(null));
					estadisticaElement.setLiquidG3T4(Types.toNumber(null));
					estadisticaElement.setLiquidG3Docr4(Types.toNumber(null));
					estadisticaElement.setLiquidG3R4(Types.toNumber(null));
					estadisticaElement.setLiquidG4Doct4(Types.toNumber(null));
					estadisticaElement.setLiquidG4T4(Types.toNumber(null));
					estadisticaElement.setLiquidG4Docr4(Types.toNumber(null));
					estadisticaElement.setLiquidG4R4(Types.toNumber(null));
					estadisticaElement.setLiquidG5Doct4(Types.toNumber(null));
					estadisticaElement.setLiquidG5T4(Types.toNumber(null));
					estadisticaElement.setLiquidG5Docr4(Types.toNumber(null));
					estadisticaElement.setLiquidG5R4(Types.toNumber(null));
					estadisticaElement.setLiquidG6Doct4(Types.toNumber(null));
					estadisticaElement.setLiquidG6T4(Types.toNumber(null));
					estadisticaElement.setLiquidG6Docr4(Types.toNumber(null));
					estadisticaElement.setLiquidG6R4(Types.toNumber(null));
					estadisticaElement.setLiquidRestoDoct4(Types.toNumber(null));
					estadisticaElement.setLiquidRestoT4(Types.toNumber(null));
					estadisticaElement.setLiquidRestoDocr4(Types.toNumber(null));
					estadisticaElement.setLiquidRestoR4(Types.toNumber(null));
				} else {
					estadisticaElement.setVerLiquidaciones(Types.toStr("S"));
				}
			}
		} else {
			// OTRO MODO
			if (estadisticaElement.getVerLiquidaciones().equals("S")) {
				ViewServices.setTabPageVisible("LIQUIDACIONES", true);
			} else {
				ViewServices.setTabPageVisible("LIQUIDACIONES", false);
			}
		}
	}

	/* Original PL/SQL code code for TRIGGER VER_BINGO.WHEN-CHECKBOX-CHANGED
	 COMPRUEBA_INDICADORES('BINGOS');
	
	IF :SYSTEM.MODE='NORMAL' THEN
	IF :ESTADISTICA.VER_BINGO='S' THEN
	--SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE REALIZA LA ESTADISTICA DE
	--BINGOS
	SET_TAB_PAGE_PROPERTY('BINGOS',VISIBLE,PROPERTY_TRUE);
	CALCULA_ESTADISTICA_BINGO;
	ELSE
	--SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE BORRA TODO LO DE BINGOS
	--SE PIDE CONFIRMACION
	MOSTRAR_MENSAJE('SUR-02000 #1#2¿Desea borrar la estadística de bingos?','D',FALSE);
	
	IF NVL(:GLOBAL.QMS$DIALOG_ANSWER,'N')='Y' THEN
	    SET_TAB_PAGE_PROPERTY('BINGOS',VISIBLE,PROPERTY_FALSE);
	
	    :BINGO_DOCT1_1T := NULL;
	    :BINGO_DOCT2_1T := NULL;
	    :BINGO_DOCT3_1T := NULL;
	    :BINGO_DOCT4_1T := NULL;
	
	    :BINGO_B1_1T := NULL;
	    :BINGO_B2_1T := NULL;
	    :BINGO_B3_1T := NULL;
	    :BINGO_B4_1T := NULL;
	
	    :BINGO_T1_1T := NULL;
	    :BINGO_T2_1T := NULL;
	    :BINGO_T3_1T := NULL;
	    :BINGO_T4_1T := NULL;
	
	    :BINGO_R1_1T := NULL;
	    :BINGO_R2_1T := NULL;
	    :BINGO_R3_1T := NULL;
	    :BINGO_R4_1T := NULL;
	
	    :BINGO_DOCT1_2T:=NULL;
	    :BINGO_DOCT2_2T:=NULL;
	    :BINGO_DOCT3_2T:=NULL;
	    :BINGO_DOCT4_2T:=NULL;
	
	    :BINGO_B1_2T := NULL;
	    :BINGO_B2_2T := NULL;
	    :BINGO_B3_2T := NULL;
	    :BINGO_B4_2T := NULL;
	
	    :BINGO_T1_2T := NULL;
	    :BINGO_T2_2T := NULL;
	    :BINGO_T3_2T := NULL;
	    :BINGO_T4_2T := NULL;
	
	    :BINGO_R1_2T := NULL;
	    :BINGO_R2_2T := NULL;
	    :BINGO_R3_2T := NULL;
	    :BINGO_R4_2T := NULL;
	
	    :BINGO_DOCT1_3T := NULL;
	    :BINGO_DOCT2_3T := NULL;
	    :BINGO_DOCT3_3T := NULL;
	    :BINGO_DOCT4_3T := NULL;
	
	    :BINGO_B1_3T := NULL;
	    :BINGO_B2_3T := NULL;
	    :BINGO_B3_3T := NULL;
	    :BINGO_B4_3T := NULL;
	
	    :BINGO_T1_3T := NULL;
	    :BINGO_T2_3T := NULL;
	    :BINGO_T3_3T := NULL;
	    :BINGO_T4_3T := NULL;
	
	    :BINGO_R1_3T := NULL;
	    :BINGO_R2_3T := NULL;
	    :BINGO_R3_3T := NULL;
	    :BINGO_R4_3T := NULL;
	
	    :BINGO_DOCT1_4T := NULL;
	    :BINGO_DOCT2_4T := NULL;
	    :BINGO_DOCT3_4T := NULL;
	    :BINGO_DOCT4_4T := NULL;
	
	    :BINGO_B1_4T := NULL;
	    :BINGO_B2_4T := NULL;
	    :BINGO_B3_4T := NULL;
	    :BINGO_B4_4T := NULL;
	
	    :BINGO_T1_4T := NULL;
	    :BINGO_T2_4T := NULL;
	    :BINGO_T3_4T := NULL;
	    :BINGO_T4_4T := NULL;
	
	    :BINGO_R1_4T := NULL;
	    :BINGO_R2_4T := NULL;
	    :BINGO_R3_4T := NULL;
	    :BINGO_R4_4T := NULL;
	
	ELSE
	    :ESTADISTICA.VER_BINGO:='S';
	END IF;
	END IF;
	
	ELSE
	IF :ESTADISTICA.VER_BINGO='S' THEN
	SET_TAB_PAGE_PROPERTY('BINGOS',VISIBLE,PROPERTY_TRUE);
	ELSE
	SET_TAB_PAGE_PROPERTY('BINGOS',VISIBLE,PROPERTY_FALSE);
	END IF;
	END IF;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * VER_BINGO.WHEN-CHECKBOX-CHANGED
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "WHEN-CHECKBOX-CHANGED", item = "VER_BINGO")
	public void verBingo_checkBoxChange() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		this.getTask().getServices().compruebaIndicadores(estadisticaElement, Types.toStr("BINGOS"));
		if (TaskServices.getMode().equals("NORMAL")) {
			if (estadisticaElement.getVerBingo().equals("S")) {
				// SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE REALIZA LA ESTADISTICA DE
				// BINGOS
				ViewServices.setTabPageVisible("BINGOS", true);
				this.getTask().getServices().calculaEstadisticaBingo(estadisticaElement);
			} else {
				// SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE BORRA TODO LO DE BINGOS
				// SE PIDE CONFIRMACION
				this.getTask().getServices().mostrarMensaje(Types.toStr("SUR-02000 #1#2¿Desea borrar la estadística de bingos?"), Types.toStr("D"), Types.toBool(NBool.False));
				if (Lib.isNull(Globals.getGlobal("QMS$DIALOG_ANSWER"), "N").equals("Y")) {
					ViewServices.setTabPageVisible("BINGOS", false);
					estadisticaElement.setBingoDoct11t(Types.toNumber(null));
					estadisticaElement.setBingoDoct21t(Types.toNumber(null));
					estadisticaElement.setBingoDoct31t(Types.toNumber(null));
					estadisticaElement.setBingoDoct41t(Types.toNumber(null));
					estadisticaElement.setBingoB11t(Types.toNumber(null));
					estadisticaElement.setBingoB21t(Types.toNumber(null));
					estadisticaElement.setBingoB31t(Types.toNumber(null));
					estadisticaElement.setBingoB41t(Types.toNumber(null));
					estadisticaElement.setBingoT11t(Types.toNumber(null));
					estadisticaElement.setBingoT21t(Types.toNumber(null));
					estadisticaElement.setBingoT31t(Types.toNumber(null));
					estadisticaElement.setBingoT41t(Types.toNumber(null));
					estadisticaElement.setBingoR11t(Types.toNumber(null));
					estadisticaElement.setBingoR21t(Types.toNumber(null));
					estadisticaElement.setBingoR31t(Types.toNumber(null));
					estadisticaElement.setBingoR41t(Types.toNumber(null));
					estadisticaElement.setBingoDoct12t(Types.toNumber(null));
					estadisticaElement.setBingoDoct22t(Types.toNumber(null));
					estadisticaElement.setBingoDoct32t(Types.toNumber(null));
					estadisticaElement.setBingoDoct42t(Types.toNumber(null));
					estadisticaElement.setBingoB12t(Types.toNumber(null));
					estadisticaElement.setBingoB22t(Types.toNumber(null));
					estadisticaElement.setBingoB32t(Types.toNumber(null));
					estadisticaElement.setBingoB42t(Types.toNumber(null));
					estadisticaElement.setBingoT12t(Types.toNumber(null));
					estadisticaElement.setBingoT22t(Types.toNumber(null));
					estadisticaElement.setBingoT32t(Types.toNumber(null));
					estadisticaElement.setBingoT42t(Types.toNumber(null));
					estadisticaElement.setBingoR12t(Types.toNumber(null));
					estadisticaElement.setBingoR22t(Types.toNumber(null));
					estadisticaElement.setBingoR32t(Types.toNumber(null));
					estadisticaElement.setBingoR42t(Types.toNumber(null));
					estadisticaElement.setBingoDoct13t(Types.toNumber(null));
					estadisticaElement.setBingoDoct23t(Types.toNumber(null));
					estadisticaElement.setBingoDoct33t(Types.toNumber(null));
					estadisticaElement.setBingoDoct43t(Types.toNumber(null));
					estadisticaElement.setBingoB13t(Types.toNumber(null));
					estadisticaElement.setBingoB23t(Types.toNumber(null));
					estadisticaElement.setBingoB33t(Types.toNumber(null));
					estadisticaElement.setBingoB43t(Types.toNumber(null));
					estadisticaElement.setBingoT13t(Types.toNumber(null));
					estadisticaElement.setBingoT23t(Types.toNumber(null));
					estadisticaElement.setBingoT33t(Types.toNumber(null));
					estadisticaElement.setBingoT43t(Types.toNumber(null));
					estadisticaElement.setBingoR13t(Types.toNumber(null));
					estadisticaElement.setBingoR23t(Types.toNumber(null));
					estadisticaElement.setBingoR33t(Types.toNumber(null));
					estadisticaElement.setBingoR43t(Types.toNumber(null));
					estadisticaElement.setBingoDoct14t(Types.toNumber(null));
					estadisticaElement.setBingoDoct24t(Types.toNumber(null));
					estadisticaElement.setBingoDoct34t(Types.toNumber(null));
					estadisticaElement.setBingoDoct44t(Types.toNumber(null));
					estadisticaElement.setBingoB14t(Types.toNumber(null));
					estadisticaElement.setBingoB24t(Types.toNumber(null));
					estadisticaElement.setBingoB34t(Types.toNumber(null));
					estadisticaElement.setBingoB44t(Types.toNumber(null));
					estadisticaElement.setBingoT14t(Types.toNumber(null));
					estadisticaElement.setBingoT24t(Types.toNumber(null));
					estadisticaElement.setBingoT34t(Types.toNumber(null));
					estadisticaElement.setBingoT44t(Types.toNumber(null));
					estadisticaElement.setBingoR14t(Types.toNumber(null));
					estadisticaElement.setBingoR24t(Types.toNumber(null));
					estadisticaElement.setBingoR34t(Types.toNumber(null));
					estadisticaElement.setBingoR44t(Types.toNumber(null));
				} else {
					estadisticaElement.setVerBingo(Types.toStr("S"));
				}
			}
		} else {
			if (estadisticaElement.getVerBingo().equals("S")) {
				ViewServices.setTabPageVisible("BINGOS", true);
			} else {
				ViewServices.setTabPageVisible("BINGOS", false);
			}
		}
	}

	/* Original PL/SQL code code for TRIGGER VER_LIQ_BINGO.WHEN-CHECKBOX-CHANGED
	 COMPRUEBA_INDICADORES('LIQ_BINGOS');
	
	IF :SYSTEM.MODE='NORMAL' THEN
	IF :ESTADISTICA.VER_LIQ_BINGO='S' THEN
	--SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE REALIZA LA ESTADISTICA DE
	--LIQUIDACIONES DE BINGOS
	SET_TAB_PAGE_PROPERTY('LIQ_BINGOS',VISIBLE,PROPERTY_TRUE);
	CALCULA_ESTADISTICA_LIQ_BINGO;
	ELSE
	--SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE BORRA TODO LO DE LIQUIDACIONES
	--SE PIDE CONFIRMACION
	MOSTRAR_MENSAJE('SUR-02000 #1#2¿Desea borrar la estadística de liquidaciones de bingos?','D',FALSE);
	IF NVL(:GLOBAL.QMS$DIALOG_ANSWER,'N')='Y' THEN
	SET_TAB_PAGE_PROPERTY('LIQ_BINGOS',VISIBLE,PROPERTY_FALSE);
	
	
	:ESTADISTICA.LIQ_BIN_G1_DOCT_TOTAL:=NULL;
	:ESTADISTICA.LIQ_BIN_G2_DOCT_TOTAL:=NULL;
	:ESTADISTICA.LIQ_BIN_G3_DOCT_TOTAL:=NULL;
	:ESTADISTICA.LIQ_BIN_G4_DOCT_TOTAL:=NULL;
	:ESTADISTICA.LIQ_BIN_G5_DOCT_TOTAL:=NULL;
	:ESTADISTICA.LIQ_BIN_G6_DOCT_TOTAL:=NULL;
	:ESTADISTICA.LIQ_BIN_RESTO_DOCT_TOTAL:=NULL;
	:ESTADISTICA.LIQ_BIN_G1_T_TOTAL:=NULL;
	:ESTADISTICA.LIQ_BIN_G2_T_TOTAL:=NULL;
	:ESTADISTICA.LIQ_BIN_G3_T_TOTAL:=NULL;
	:ESTADISTICA.LIQ_BIN_G4_T_TOTAL:=NULL;
	:ESTADISTICA.LIQ_BIN_G5_T_TOTAL:=NULL;
	:ESTADISTICA.LIQ_BIN_G6_T_TOTAL:=NULL;
	:ESTADISTICA.LIQ_BIN_RESTO_T_TOTAL:=NULL;
	
	:ESTADISTICA.LIQ_BIN_G1_DOCR1:=NULL;
	:ESTADISTICA.LIQ_BIN_G1_DOCR2:=NULL;
	:ESTADISTICA.LIQ_BIN_G1_DOCR3:=NULL;
	:ESTADISTICA.LIQ_BIN_G1_DOCR4:=NULL;
	:ESTADISTICA.LIQ_BIN_G2_DOCR1:=NULL;
	:ESTADISTICA.LIQ_BIN_G2_DOCR2:=NULL;
	:ESTADISTICA.LIQ_BIN_G2_DOCR3:=NULL;
	:ESTADISTICA.LIQ_BIN_G2_DOCR4:=NULL;
	:ESTADISTICA.LIQ_BIN_G3_DOCR1:=NULL;
	:ESTADISTICA.LIQ_BIN_G3_DOCR2:=NULL;
	:ESTADISTICA.LIQ_BIN_G3_DOCR3:=NULL;
	:ESTADISTICA.LIQ_BIN_G3_DOCR4:=NULL;
	:ESTADISTICA.LIQ_BIN_G4_DOCR1:=NULL;
	:ESTADISTICA.LIQ_BIN_G4_DOCR2:=NULL;
	:ESTADISTICA.LIQ_BIN_G4_DOCR3:=NULL;
	:ESTADISTICA.LIQ_BIN_G4_DOCR4:=NULL;
	:ESTADISTICA.LIQ_BIN_G5_DOCR1:=NULL;
	:ESTADISTICA.LIQ_BIN_G5_DOCR2:=NULL;
	:ESTADISTICA.LIQ_BIN_G5_DOCR3:=NULL;
	:ESTADISTICA.LIQ_BIN_G5_DOCR4:=NULL;
	:ESTADISTICA.LIQ_BIN_G6_DOCR1:=NULL;
	:ESTADISTICA.LIQ_BIN_G6_DOCR2:=NULL;
	:ESTADISTICA.LIQ_BIN_G6_DOCR3:=NULL;
	:ESTADISTICA.LIQ_BIN_G6_DOCR4:=NULL;
	:ESTADISTICA.LIQ_BIN_RESTO_DOCR1:=NULL;
	:ESTADISTICA.LIQ_BIN_RESTO_DOCR2:=NULL;
	:ESTADISTICA.LIQ_BIN_RESTO_DOCR3:=NULL;
	:ESTADISTICA.LIQ_BIN_RESTO_DOCR4:=NULL;
	
	:ESTADISTICA.LIQ_BIN_G1_R1:=NULL;
	:ESTADISTICA.LIQ_BIN_G1_R2:=NULL;
	:ESTADISTICA.LIQ_BIN_G1_R3:=NULL;
	:ESTADISTICA.LIQ_BIN_G1_R4:=NULL;
	:ESTADISTICA.LIQ_BIN_G2_R1:=NULL;
	:ESTADISTICA.LIQ_BIN_G2_R2:=NULL;
	:ESTADISTICA.LIQ_BIN_G2_R3:=NULL;
	:ESTADISTICA.LIQ_BIN_G2_R4:=NULL;
	:ESTADISTICA.LIQ_BIN_G3_R1:=NULL;
	:ESTADISTICA.LIQ_BIN_G3_R2:=NULL;
	:ESTADISTICA.LIQ_BIN_G3_R3:=NULL;
	:ESTADISTICA.LIQ_BIN_G3_R4:=NULL;
	:ESTADISTICA.LIQ_BIN_G4_R1:=NULL;
	:ESTADISTICA.LIQ_BIN_G4_R2:=NULL;
	:ESTADISTICA.LIQ_BIN_G4_R3:=NULL;
	:ESTADISTICA.LIQ_BIN_G4_R4:=NULL;
	:ESTADISTICA.LIQ_BIN_G5_R1:=NULL;
	:ESTADISTICA.LIQ_BIN_G5_R2:=NULL;
	:ESTADISTICA.LIQ_BIN_G5_R3:=NULL;
	:ESTADISTICA.LIQ_BIN_G5_R4:=NULL;
	:ESTADISTICA.LIQ_BIN_G6_R1:=NULL;
	:ESTADISTICA.LIQ_BIN_G6_R2:=NULL;
	:ESTADISTICA.LIQ_BIN_G6_R3:=NULL;
	:ESTADISTICA.LIQ_BIN_G6_R4:=NULL;
	:ESTADISTICA.LIQ_BIN_RESTO_R1:=NULL;
	:ESTADISTICA.LIQ_BIN_RESTO_R2:=NULL;
	:ESTADISTICA.LIQ_BIN_RESTO_R3:=NULL;
	:ESTADISTICA.LIQ_BIN_RESTO_R4:=NULL;
	
	
	ELSE
	:ESTADISTICA.VER_LIQ_BINGO:='S';
	END IF;
	END IF;
	
	ELSE --OTRO MODO
	
	IF :ESTADISTICA.VER_LIQ_BINGO='S' THEN
	SET_TAB_PAGE_PROPERTY('LIQ_BINGOS',VISIBLE,PROPERTY_TRUE);
	ELSE
	SET_TAB_PAGE_PROPERTY('LIQ_BINGOS',VISIBLE,PROPERTY_FALSE);
	END IF;
	END IF;
	
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * VER_LIQ_BINGO.WHEN-CHECKBOX-CHANGED
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "WHEN-CHECKBOX-CHANGED", item = "VER_LIQ_BINGO")
	public void verLiqBingo_checkBoxChange() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		this.getTask().getServices().compruebaIndicadores(estadisticaElement, Types.toStr("LIQ_BINGOS"));
		if (TaskServices.getMode().equals("NORMAL")) {
			if (estadisticaElement.getVerLiqBingo().equals("S")) {
				// SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE REALIZA LA ESTADISTICA DE
				// LIQUIDACIONES DE BINGOS
				ViewServices.setTabPageVisible("LIQ_BINGOS", true);
				this.getTask().getServices().calculaEstadisticaLiqBingo(estadisticaElement);
			} else {
				// SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE BORRA TODO LO DE LIQUIDACIONES
				// SE PIDE CONFIRMACION
				this.getTask().getServices().mostrarMensaje(Types.toStr("SUR-02000 #1#2¿Desea borrar la estadística de liquidaciones de bingos?"), Types.toStr("D"), Types.toBool(NBool.False));
				if (Lib.isNull(Globals.getGlobal("QMS$DIALOG_ANSWER"), "N").equals("Y")) {
					ViewServices.setTabPageVisible("LIQ_BINGOS", false);
					estadisticaElement.setLiqBinG1DoctTotal(Types.toNumber(null));
					estadisticaElement.setLiqBinG2DoctTotal(Types.toNumber(null));
					estadisticaElement.setLiqBinG3DoctTotal(Types.toNumber(null));
					estadisticaElement.setLiqBinG4DoctTotal(Types.toNumber(null));
					estadisticaElement.setLiqBinG5DoctTotal(Types.toNumber(null));
					estadisticaElement.setLiqBinG6DoctTotal(Types.toNumber(null));
					estadisticaElement.setLiqBinRestoDoctTotal(Types.toNumber(null));
					estadisticaElement.setLiqBinG1TTotal(Types.toNumber(null));
					estadisticaElement.setLiqBinG2TTotal(Types.toNumber(null));
					estadisticaElement.setLiqBinG3TTotal(Types.toNumber(null));
					estadisticaElement.setLiqBinG4TTotal(Types.toNumber(null));
					estadisticaElement.setLiqBinG5TTotal(Types.toNumber(null));
					estadisticaElement.setLiqBinG6TTotal(Types.toNumber(null));
					estadisticaElement.setLiqBinRestoTTotal(Types.toNumber(null));
					estadisticaElement.setLiqBinG1Docr1(Types.toNumber(null));
					estadisticaElement.setLiqBinG1Docr2(Types.toNumber(null));
					estadisticaElement.setLiqBinG1Docr3(Types.toNumber(null));
					estadisticaElement.setLiqBinG1Docr4(Types.toNumber(null));
					estadisticaElement.setLiqBinG2Docr1(Types.toNumber(null));
					estadisticaElement.setLiqBinG2Docr2(Types.toNumber(null));
					estadisticaElement.setLiqBinG2Docr3(Types.toNumber(null));
					estadisticaElement.setLiqBinG2Docr4(Types.toNumber(null));
					estadisticaElement.setLiqBinG3Docr1(Types.toNumber(null));
					estadisticaElement.setLiqBinG3Docr2(Types.toNumber(null));
					estadisticaElement.setLiqBinG3Docr3(Types.toNumber(null));
					estadisticaElement.setLiqBinG3Docr4(Types.toNumber(null));
					estadisticaElement.setLiqBinG4Docr1(Types.toNumber(null));
					estadisticaElement.setLiqBinG4Docr2(Types.toNumber(null));
					estadisticaElement.setLiqBinG4Docr3(Types.toNumber(null));
					estadisticaElement.setLiqBinG4Docr4(Types.toNumber(null));
					estadisticaElement.setLiqBinG5Docr1(Types.toNumber(null));
					estadisticaElement.setLiqBinG5Docr2(Types.toNumber(null));
					estadisticaElement.setLiqBinG5Docr3(Types.toNumber(null));
					estadisticaElement.setLiqBinG5Docr4(Types.toNumber(null));
					estadisticaElement.setLiqBinG6Docr1(Types.toNumber(null));
					estadisticaElement.setLiqBinG6Docr2(Types.toNumber(null));
					estadisticaElement.setLiqBinG6Docr3(Types.toNumber(null));
					estadisticaElement.setLiqBinG6Docr4(Types.toNumber(null));
					estadisticaElement.setLiqBinRestoDocr1(Types.toNumber(null));
					estadisticaElement.setLiqBinRestoDocr2(Types.toNumber(null));
					estadisticaElement.setLiqBinRestoDocr3(Types.toNumber(null));
					estadisticaElement.setLiqBinRestoDocr4(Types.toNumber(null));
					estadisticaElement.setLiqBinG1R1(Types.toNumber(null));
					estadisticaElement.setLiqBinG1R2(Types.toNumber(null));
					estadisticaElement.setLiqBinG1R3(Types.toNumber(null));
					estadisticaElement.setLiqBinG1R4(Types.toNumber(null));
					estadisticaElement.setLiqBinG2R1(Types.toNumber(null));
					estadisticaElement.setLiqBinG2R2(Types.toNumber(null));
					estadisticaElement.setLiqBinG2R3(Types.toNumber(null));
					estadisticaElement.setLiqBinG2R4(Types.toNumber(null));
					estadisticaElement.setLiqBinG3R1(Types.toNumber(null));
					estadisticaElement.setLiqBinG3R2(Types.toNumber(null));
					estadisticaElement.setLiqBinG3R3(Types.toNumber(null));
					estadisticaElement.setLiqBinG3R4(Types.toNumber(null));
					estadisticaElement.setLiqBinG4R1(Types.toNumber(null));
					estadisticaElement.setLiqBinG4R2(Types.toNumber(null));
					estadisticaElement.setLiqBinG4R3(Types.toNumber(null));
					estadisticaElement.setLiqBinG4R4(Types.toNumber(null));
					estadisticaElement.setLiqBinG5R1(Types.toNumber(null));
					estadisticaElement.setLiqBinG5R2(Types.toNumber(null));
					estadisticaElement.setLiqBinG5R3(Types.toNumber(null));
					estadisticaElement.setLiqBinG5R4(Types.toNumber(null));
					estadisticaElement.setLiqBinG6R1(Types.toNumber(null));
					estadisticaElement.setLiqBinG6R2(Types.toNumber(null));
					estadisticaElement.setLiqBinG6R3(Types.toNumber(null));
					estadisticaElement.setLiqBinG6R4(Types.toNumber(null));
					estadisticaElement.setLiqBinRestoR1(Types.toNumber(null));
					estadisticaElement.setLiqBinRestoR2(Types.toNumber(null));
					estadisticaElement.setLiqBinRestoR3(Types.toNumber(null));
					estadisticaElement.setLiqBinRestoR4(Types.toNumber(null));
				} else {
					estadisticaElement.setVerLiqBingo(Types.toStr("S"));
				}
			}
		} else {
			// OTRO MODO
			if (estadisticaElement.getVerLiqBingo().equals("S")) {
				ViewServices.setTabPageVisible("LIQ_BINGOS", true);
			} else {
				ViewServices.setTabPageVisible("LIQ_BINGOS", false);
			}
		}
	}

	/* Original PL/SQL code code for TRIGGER VER_CASINO.WHEN-CHECKBOX-CHANGED
	 COMPRUEBA_INDICADORES('CASINOS');
	
	IF :SYSTEM.MODE='NORMAL' THEN
	IF :ESTADISTICA.VER_CASINO='S' THEN
	--SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE REALIZA LA ESTADISTICA DE
	--CASINOS
	SET_TAB_PAGE_PROPERTY('CASINOS',VISIBLE,PROPERTY_TRUE);
	
	CALCULA_ESTADISTICA_CASINO;
	ELSE
	--SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE BORRA TODO LO DE CASINOS
	--SE PIDE CONFIRMACION
	MOSTRAR_MENSAJE('SUR-02000 #1#2¿Desea borrar la estadística de casinos?','D',FALSE);
	IF NVL(:GLOBAL.QMS$DIALOG_ANSWER,'N')='Y' THEN
	SET_TAB_PAGE_PROPERTY('CASINOS',VISIBLE,PROPERTY_FALSE);
	
	:CASINO_DOCT1:=NULL;
	:CASINO_T1:=NULL;
	:CASINO_DOCR1:=NULL;
	:CASINO_R1:=NULL;
	:CASINO_DOCT2:=NULL;
	:CASINO_T2:=NULL;
	:CASINO_DOCR2:=NULL;
	:CASINO_R2:=NULL;
	:CASINO_DOCT3:=NULL;
	:CASINO_T3:=NULL;
	:CASINO_DOCR3:=NULL;
	:CASINO_R3:=NULL;
	:CASINO_DOCT4:=NULL;
	:CASINO_T4:=NULL;
	:CASINO_DOCR4:=NULL;
	:CASINO_R4:=NULL;
	
	ELSE
	:ESTADISTICA.VER_CASINO:='S';
	END IF;
	END IF;
	ELSE
	IF :ESTADISTICA.VER_CASINO='S' THEN
	SET_TAB_PAGE_PROPERTY('CASINOS',VISIBLE,PROPERTY_TRUE);
	ELSE
	SET_TAB_PAGE_PROPERTY('CASINOS',VISIBLE,PROPERTY_FALSE);
	END IF;
	END IF;
	
	
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * VER_CASINO.WHEN-CHECKBOX-CHANGED
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "WHEN-CHECKBOX-CHANGED", item = "VER_CASINO")
	public void verCasino_checkBoxChange() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		this.getTask().getServices().compruebaIndicadores(estadisticaElement, Types.toStr("CASINOS"));
		if (TaskServices.getMode().equals("NORMAL")) {
			if (estadisticaElement.getVerCasino().equals("S")) {
				// SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE REALIZA LA ESTADISTICA DE
				// CASINOS
				ViewServices.setTabPageVisible("CASINOS", true);
				this.getTask().getServices().calculaEstadisticaCasino(estadisticaElement);
			} else {
				// SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE BORRA TODO LO DE CASINOS
				// SE PIDE CONFIRMACION
				this.getTask().getServices().mostrarMensaje(Types.toStr("SUR-02000 #1#2¿Desea borrar la estadística de casinos?"), Types.toStr("D"), Types.toBool(NBool.False));
				if (Lib.isNull(Globals.getGlobal("QMS$DIALOG_ANSWER"), "N").equals("Y")) {
					ViewServices.setTabPageVisible("CASINOS", false);
					estadisticaElement.setCasinoDoct1(Types.toNumber(null));
					estadisticaElement.setCasinoT1(Types.toNumber(null));
					estadisticaElement.setCasinoDocr1(Types.toNumber(null));
					estadisticaElement.setCasinoR1(Types.toNumber(null));
					estadisticaElement.setCasinoDoct2(Types.toNumber(null));
					estadisticaElement.setCasinoT2(Types.toNumber(null));
					estadisticaElement.setCasinoDocr2(Types.toNumber(null));
					estadisticaElement.setCasinoR2(Types.toNumber(null));
					estadisticaElement.setCasinoDoct3(Types.toNumber(null));
					estadisticaElement.setCasinoT3(Types.toNumber(null));
					estadisticaElement.setCasinoDocr3(Types.toNumber(null));
					estadisticaElement.setCasinoR3(Types.toNumber(null));
					estadisticaElement.setCasinoDoct4(Types.toNumber(null));
					estadisticaElement.setCasinoT4(Types.toNumber(null));
					estadisticaElement.setCasinoDocr4(Types.toNumber(null));
					estadisticaElement.setCasinoR4(Types.toNumber(null));
				} else {
					estadisticaElement.setVerCasino(Types.toStr("S"));
				}
			}
		} else {
			if (estadisticaElement.getVerCasino().equals("S")) {
				ViewServices.setTabPageVisible("CASINOS", true);
			} else {
				ViewServices.setTabPageVisible("CASINOS", false);
			}
		}
	}

	/* Original PL/SQL code code for TRIGGER VER_LIQ_CASINO.WHEN-CHECKBOX-CHANGED
	 COMPRUEBA_INDICADORES('LIQ_CASINOS');
	
	IF :SYSTEM.MODE='NORMAL' THEN
	IF :ESTADISTICA.VER_LIQ_CASINO='S' THEN
	--SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE REALIZA LA ESTADISTICA DE
	--LIQUIDACIONES DE CASINOS
	SET_TAB_PAGE_PROPERTY('LIQ_CASINOS',VISIBLE,PROPERTY_TRUE);
	CALCULA_ESTADISTICA_LIQ_CASINO;
	ELSE
	--SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE BORRA TODO LO DE LIQUIDACIONES DE CASINOS
	--SE PIDE CONFIRMACION
	MOSTRAR_MENSAJE('SUR-02000 #1#2¿Desea borrar la estadística de liquidaciones de casinos?','D',FALSE);
	IF NVL(:GLOBAL.QMS$DIALOG_ANSWER,'N')='Y' THEN
	SET_TAB_PAGE_PROPERTY('LIQ_CASINOS',VISIBLE,PROPERTY_FALSE);
	
	:ESTADISTICA.LIQ_CAS_G1_DOCT1:=NULL;
	:ESTADISTICA.LIQ_CAS_G1_T1:=NULL;
	:ESTADISTICA.LIQ_CAS_G1_DOCR1:=NULL;
	:ESTADISTICA.LIQ_CAS_G1_R1:=NULL;
	:ESTADISTICA.LIQ_CAS_G2_DOCT1:=NULL;
	:ESTADISTICA.LIQ_CAS_G2_T1:=NULL;
	:ESTADISTICA.LIQ_CAS_G2_DOCR1:=NULL;
	:ESTADISTICA.LIQ_CAS_G2_R1:=NULL;
	:ESTADISTICA.LIQ_CAS_G3_DOCT1:=NULL;
	:ESTADISTICA.LIQ_CAS_G3_T1:=NULL;
	:ESTADISTICA.LIQ_CAS_G3_DOCR1:=NULL;
	:ESTADISTICA.LIQ_CAS_G3_R1:=NULL;
	:ESTADISTICA.LIQ_CAS_G4_DOCT1:=NULL;
	:ESTADISTICA.LIQ_CAS_G4_T1:=NULL;
	:ESTADISTICA.LIQ_CAS_G4_DOCR1:=NULL;
	:ESTADISTICA.LIQ_CAS_G4_R1:=NULL;
	:ESTADISTICA.LIQ_CAS_G5_DOCT1:=NULL;
	:ESTADISTICA.LIQ_CAS_G5_T1:=NULL;
	:ESTADISTICA.LIQ_CAS_G5_DOCR1:=NULL;
	:ESTADISTICA.LIQ_CAS_G5_R1:=NULL;
	:ESTADISTICA.LIQ_CAS_G6_DOCT1:=NULL;
	:ESTADISTICA.LIQ_CAS_G6_T1:=NULL;
	:ESTADISTICA.LIQ_CAS_G6_DOCR1:=NULL;
	:ESTADISTICA.LIQ_CAS_G6_R1:=NULL;
	:ESTADISTICA.LIQ_CAS_RESTO_DOCT1:=NULL;
	:ESTADISTICA.LIQ_CAS_RESTO_T1:=NULL;
	:ESTADISTICA.LIQ_CAS_RESTO_DOCR1:=NULL;
	:ESTADISTICA.LIQ_CAS_RESTO_R1:=NULL;
	
	:ESTADISTICA.LIQ_CAS_G1_DOCT2:=NULL;
	:ESTADISTICA.LIQ_CAS_G1_T2:=NULL;
	:ESTADISTICA.LIQ_CAS_G1_DOCR2:=NULL;
	:ESTADISTICA.LIQ_CAS_G1_R2:=NULL;
	:ESTADISTICA.LIQ_CAS_G2_DOCT2:=NULL;
	:ESTADISTICA.LIQ_CAS_G2_T2:=NULL;
	:ESTADISTICA.LIQ_CAS_G2_DOCR2:=NULL;
	:ESTADISTICA.LIQ_CAS_G2_R2:=NULL;
	:ESTADISTICA.LIQ_CAS_G3_DOCT2:=NULL;
	:ESTADISTICA.LIQ_CAS_G3_T2:=NULL;
	:ESTADISTICA.LIQ_CAS_G3_DOCR2:=NULL;
	:ESTADISTICA.LIQ_CAS_G3_R2:=NULL;
	:ESTADISTICA.LIQ_CAS_G4_DOCT2:=NULL;
	:ESTADISTICA.LIQ_CAS_G4_T2:=NULL;
	:ESTADISTICA.LIQ_CAS_G4_DOCR2:=NULL;
	:ESTADISTICA.LIQ_CAS_G4_R2:=NULL;
	:ESTADISTICA.LIQ_CAS_G5_DOCT2:=NULL;
	:ESTADISTICA.LIQ_CAS_G5_T2:=NULL;
	:ESTADISTICA.LIQ_CAS_G5_DOCR2:=NULL;
	:ESTADISTICA.LIQ_CAS_G5_R2:=NULL;
	:ESTADISTICA.LIQ_CAS_G6_DOCT2:=NULL;
	:ESTADISTICA.LIQ_CAS_G6_T2:=NULL;
	:ESTADISTICA.LIQ_CAS_G6_DOCR2:=NULL;
	:ESTADISTICA.LIQ_CAS_G6_R2:=NULL;
	:ESTADISTICA.LIQ_CAS_RESTO_DOCT2:=NULL;
	:ESTADISTICA.LIQ_CAS_RESTO_T2:=NULL;
	:ESTADISTICA.LIQ_CAS_RESTO_DOCR2:=NULL;
	:ESTADISTICA.LIQ_CAS_RESTO_R2:=NULL;
	
	:ESTADISTICA.LIQ_CAS_G1_DOCT3:=NULL;
	:ESTADISTICA.LIQ_CAS_G1_T3:=NULL;
	:ESTADISTICA.LIQ_CAS_G1_DOCR3:=NULL;
	:ESTADISTICA.LIQ_CAS_G1_R3:=NULL;
	:ESTADISTICA.LIQ_CAS_G2_DOCT3:=NULL;
	:ESTADISTICA.LIQ_CAS_G2_T3:=NULL;
	:ESTADISTICA.LIQ_CAS_G2_DOCR3:=NULL;
	:ESTADISTICA.LIQ_CAS_G2_R3:=NULL;
	:ESTADISTICA.LIQ_CAS_G3_DOCT3:=NULL;
	:ESTADISTICA.LIQ_CAS_G3_T3:=NULL;
	:ESTADISTICA.LIQ_CAS_G3_DOCR3:=NULL;
	:ESTADISTICA.LIQ_CAS_G3_R3:=NULL;
	:ESTADISTICA.LIQ_CAS_G4_DOCT3:=NULL;
	:ESTADISTICA.LIQ_CAS_G4_T3:=NULL;
	:ESTADISTICA.LIQ_CAS_G4_DOCR3:=NULL;
	:ESTADISTICA.LIQ_CAS_G4_R3:=NULL;
	:ESTADISTICA.LIQ_CAS_G5_DOCT3:=NULL;
	:ESTADISTICA.LIQ_CAS_G5_T3:=NULL;
	:ESTADISTICA.LIQ_CAS_G5_DOCR3:=NULL;
	:ESTADISTICA.LIQ_CAS_G5_R3:=NULL;
	:ESTADISTICA.LIQ_CAS_G6_DOCT3:=NULL;
	:ESTADISTICA.LIQ_CAS_G6_T3:=NULL;
	:ESTADISTICA.LIQ_CAS_G6_DOCR3:=NULL;
	:ESTADISTICA.LIQ_CAS_G6_R3:=NULL;
	:ESTADISTICA.LIQ_CAS_RESTO_DOCT3:=NULL;
	:ESTADISTICA.LIQ_CAS_RESTO_T3:=NULL;
	:ESTADISTICA.LIQ_CAS_RESTO_DOCR3:=NULL;
	:ESTADISTICA.LIQ_CAS_RESTO_R3:=NULL;
	
	:ESTADISTICA.LIQ_CAS_G1_DOCT4:=NULL;
	:ESTADISTICA.LIQ_CAS_G1_T4:=NULL;
	:ESTADISTICA.LIQ_CAS_G1_DOCR4:=NULL;
	:ESTADISTICA.LIQ_CAS_G1_R4:=NULL;
	:ESTADISTICA.LIQ_CAS_G2_DOCT4:=NULL;
	:ESTADISTICA.LIQ_CAS_G2_T4:=NULL;
	:ESTADISTICA.LIQ_CAS_G2_DOCR4:=NULL;
	:ESTADISTICA.LIQ_CAS_G2_R4:=NULL;
	:ESTADISTICA.LIQ_CAS_G3_DOCT4:=NULL;
	:ESTADISTICA.LIQ_CAS_G3_T4:=NULL;
	:ESTADISTICA.LIQ_CAS_G3_DOCR4:=NULL;
	:ESTADISTICA.LIQ_CAS_G3_R4:=NULL;
	:ESTADISTICA.LIQ_CAS_G4_DOCT4:=NULL;
	:ESTADISTICA.LIQ_CAS_G4_T4:=NULL;
	:ESTADISTICA.LIQ_CAS_G4_DOCR4:=NULL;
	:ESTADISTICA.LIQ_CAS_G4_R4:=NULL;
	:ESTADISTICA.LIQ_CAS_G5_DOCT4:=NULL;
	:ESTADISTICA.LIQ_CAS_G5_T4:=NULL;
	:ESTADISTICA.LIQ_CAS_G5_DOCR4:=NULL;
	:ESTADISTICA.LIQ_CAS_G5_R4:=NULL;
	:ESTADISTICA.LIQ_CAS_G6_DOCT4:=NULL;
	:ESTADISTICA.LIQ_CAS_G6_T4:=NULL;
	:ESTADISTICA.LIQ_CAS_G6_DOCR4:=NULL;
	:ESTADISTICA.LIQ_CAS_G6_R4:=NULL;
	:ESTADISTICA.LIQ_CAS_RESTO_DOCT4:=NULL;
	:ESTADISTICA.LIQ_CAS_RESTO_T4:=NULL;
	:ESTADISTICA.LIQ_CAS_RESTO_DOCR4:=NULL;
	:ESTADISTICA.LIQ_CAS_RESTO_R4:=NULL;
	
	
	
	
	ELSE
	:ESTADISTICA.VER_LIQ_CASINO:='S';
	END IF;
	END IF;
	
	ELSE --OTRO MODO
	
	IF :ESTADISTICA.VER_LIQ_CASINO='S' THEN
	SET_TAB_PAGE_PROPERTY('LIQ_CASINOS',VISIBLE,PROPERTY_TRUE);
	ELSE
	SET_TAB_PAGE_PROPERTY('LIQ_CASINOS',VISIBLE,PROPERTY_FALSE);
	END IF;
	END IF;
	
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * VER_LIQ_CASINO.WHEN-CHECKBOX-CHANGED
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "WHEN-CHECKBOX-CHANGED", item = "VER_LIQ_CASINO")
	public void verLiqCasino_checkBoxChange() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		this.getTask().getServices().compruebaIndicadores(estadisticaElement, Types.toStr("LIQ_CASINOS"));
		if (TaskServices.getMode().equals("NORMAL")) {
			if (estadisticaElement.getVerLiqCasino().equals("S")) {
				// SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE REALIZA LA ESTADISTICA DE
				// LIQUIDACIONES DE CASINOS
				ViewServices.setTabPageVisible("LIQ_CASINOS", true);
				this.getTask().getServices().calculaEstadisticaLiqCasino(estadisticaElement);
			} else {
				// SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE BORRA TODO LO DE LIQUIDACIONES DE CASINOS
				// SE PIDE CONFIRMACION
				this.getTask().getServices().mostrarMensaje(Types.toStr("SUR-02000 #1#2¿Desea borrar la estadística de liquidaciones de casinos?"), Types.toStr("D"), Types.toBool(NBool.False));
				if (Lib.isNull(Globals.getGlobal("QMS$DIALOG_ANSWER"), "N").equals("Y")) {
					ViewServices.setTabPageVisible("LIQ_CASINOS", false);
					estadisticaElement.setLiqCasG1Doct1(Types.toNumber(null));
					estadisticaElement.setLiqCasG1T1(Types.toNumber(null));
					estadisticaElement.setLiqCasG1Docr1(Types.toNumber(null));
					estadisticaElement.setLiqCasG1R1(Types.toNumber(null));
					estadisticaElement.setLiqCasG2Doct1(Types.toNumber(null));
					estadisticaElement.setLiqCasG2T1(Types.toNumber(null));
					estadisticaElement.setLiqCasG2Docr1(Types.toNumber(null));
					estadisticaElement.setLiqCasG2R1(Types.toNumber(null));
					estadisticaElement.setLiqCasG3Doct1(Types.toNumber(null));
					estadisticaElement.setLiqCasG3T1(Types.toNumber(null));
					estadisticaElement.setLiqCasG3Docr1(Types.toNumber(null));
					estadisticaElement.setLiqCasG3R1(Types.toNumber(null));
					estadisticaElement.setLiqCasG4Doct1(Types.toNumber(null));
					estadisticaElement.setLiqCasG4T1(Types.toNumber(null));
					estadisticaElement.setLiqCasG4Docr1(Types.toNumber(null));
					estadisticaElement.setLiqCasG4R1(Types.toNumber(null));
					estadisticaElement.setLiqCasG5Doct1(Types.toNumber(null));
					estadisticaElement.setLiqCasG5T1(Types.toNumber(null));
					estadisticaElement.setLiqCasG5Docr1(Types.toNumber(null));
					estadisticaElement.setLiqCasG5R1(Types.toNumber(null));
					estadisticaElement.setLiqCasG6Doct1(Types.toNumber(null));
					estadisticaElement.setLiqCasG6T1(Types.toNumber(null));
					estadisticaElement.setLiqCasG6Docr1(Types.toNumber(null));
					estadisticaElement.setLiqCasG6R1(Types.toNumber(null));
					estadisticaElement.setLiqCasRestoDoct1(Types.toNumber(null));
					estadisticaElement.setLiqCasRestoT1(Types.toNumber(null));
					estadisticaElement.setLiqCasRestoDocr1(Types.toNumber(null));
					estadisticaElement.setLiqCasRestoR1(Types.toNumber(null));
					estadisticaElement.setLiqCasG1Doct2(Types.toNumber(null));
					estadisticaElement.setLiqCasG1T2(Types.toNumber(null));
					estadisticaElement.setLiqCasG1Docr2(Types.toNumber(null));
					estadisticaElement.setLiqCasG1R2(Types.toNumber(null));
					estadisticaElement.setLiqCasG2Doct2(Types.toNumber(null));
					estadisticaElement.setLiqCasG2T2(Types.toNumber(null));
					estadisticaElement.setLiqCasG2Docr2(Types.toNumber(null));
					estadisticaElement.setLiqCasG2R2(Types.toNumber(null));
					estadisticaElement.setLiqCasG3Doct2(Types.toNumber(null));
					estadisticaElement.setLiqCasG3T2(Types.toNumber(null));
					estadisticaElement.setLiqCasG3Docr2(Types.toNumber(null));
					estadisticaElement.setLiqCasG3R2(Types.toNumber(null));
					estadisticaElement.setLiqCasG4Doct2(Types.toNumber(null));
					estadisticaElement.setLiqCasG4T2(Types.toNumber(null));
					estadisticaElement.setLiqCasG4Docr2(Types.toNumber(null));
					estadisticaElement.setLiqCasG4R2(Types.toNumber(null));
					estadisticaElement.setLiqCasG5Doct2(Types.toNumber(null));
					estadisticaElement.setLiqCasG5T2(Types.toNumber(null));
					estadisticaElement.setLiqCasG5Docr2(Types.toNumber(null));
					estadisticaElement.setLiqCasG5R2(Types.toNumber(null));
					estadisticaElement.setLiqCasG6Doct2(Types.toNumber(null));
					estadisticaElement.setLiqCasG6T2(Types.toNumber(null));
					estadisticaElement.setLiqCasG6Docr2(Types.toNumber(null));
					estadisticaElement.setLiqCasG6R2(Types.toNumber(null));
					estadisticaElement.setLiqCasRestoDoct2(Types.toNumber(null));
					estadisticaElement.setLiqCasRestoT2(Types.toNumber(null));
					estadisticaElement.setLiqCasRestoDocr2(Types.toNumber(null));
					estadisticaElement.setLiqCasRestoR2(Types.toNumber(null));
					estadisticaElement.setLiqCasG1Doct3(Types.toNumber(null));
					estadisticaElement.setLiqCasG1T3(Types.toNumber(null));
					estadisticaElement.setLiqCasG1Docr3(Types.toNumber(null));
					estadisticaElement.setLiqCasG1R3(Types.toNumber(null));
					estadisticaElement.setLiqCasG2Doct3(Types.toNumber(null));
					estadisticaElement.setLiqCasG2T3(Types.toNumber(null));
					estadisticaElement.setLiqCasG2Docr3(Types.toNumber(null));
					estadisticaElement.setLiqCasG2R3(Types.toNumber(null));
					estadisticaElement.setLiqCasG3Doct3(Types.toNumber(null));
					estadisticaElement.setLiqCasG3T3(Types.toNumber(null));
					estadisticaElement.setLiqCasG3Docr3(Types.toNumber(null));
					estadisticaElement.setLiqCasG3R3(Types.toNumber(null));
					estadisticaElement.setLiqCasG4Doct3(Types.toNumber(null));
					estadisticaElement.setLiqCasG4T3(Types.toNumber(null));
					estadisticaElement.setLiqCasG4Docr3(Types.toNumber(null));
					estadisticaElement.setLiqCasG4R3(Types.toNumber(null));
					estadisticaElement.setLiqCasG5Doct3(Types.toNumber(null));
					estadisticaElement.setLiqCasG5T3(Types.toNumber(null));
					estadisticaElement.setLiqCasG5Docr3(Types.toNumber(null));
					estadisticaElement.setLiqCasG5R3(Types.toNumber(null));
					estadisticaElement.setLiqCasG6Doct3(Types.toNumber(null));
					estadisticaElement.setLiqCasG6T3(Types.toNumber(null));
					estadisticaElement.setLiqCasG6Docr3(Types.toNumber(null));
					estadisticaElement.setLiqCasG6R3(Types.toNumber(null));
					estadisticaElement.setLiqCasRestoDoct3(Types.toNumber(null));
					estadisticaElement.setLiqCasRestoT3(Types.toNumber(null));
					estadisticaElement.setLiqCasRestoDocr3(Types.toNumber(null));
					estadisticaElement.setLiqCasRestoR3(Types.toNumber(null));
					estadisticaElement.setLiqCasG1Doct4(Types.toNumber(null));
					estadisticaElement.setLiqCasG1T4(Types.toNumber(null));
					estadisticaElement.setLiqCasG1Docr4(Types.toNumber(null));
					estadisticaElement.setLiqCasG1R4(Types.toNumber(null));
					estadisticaElement.setLiqCasG2Doct4(Types.toNumber(null));
					estadisticaElement.setLiqCasG2T4(Types.toNumber(null));
					estadisticaElement.setLiqCasG2Docr4(Types.toNumber(null));
					estadisticaElement.setLiqCasG2R4(Types.toNumber(null));
					estadisticaElement.setLiqCasG3Doct4(Types.toNumber(null));
					estadisticaElement.setLiqCasG3T4(Types.toNumber(null));
					estadisticaElement.setLiqCasG3Docr4(Types.toNumber(null));
					estadisticaElement.setLiqCasG3R4(Types.toNumber(null));
					estadisticaElement.setLiqCasG4Doct4(Types.toNumber(null));
					estadisticaElement.setLiqCasG4T4(Types.toNumber(null));
					estadisticaElement.setLiqCasG4Docr4(Types.toNumber(null));
					estadisticaElement.setLiqCasG4R4(Types.toNumber(null));
					estadisticaElement.setLiqCasG5Doct4(Types.toNumber(null));
					estadisticaElement.setLiqCasG5T4(Types.toNumber(null));
					estadisticaElement.setLiqCasG5Docr4(Types.toNumber(null));
					estadisticaElement.setLiqCasG5R4(Types.toNumber(null));
					estadisticaElement.setLiqCasG6Doct4(Types.toNumber(null));
					estadisticaElement.setLiqCasG6T4(Types.toNumber(null));
					estadisticaElement.setLiqCasG6Docr4(Types.toNumber(null));
					estadisticaElement.setLiqCasG6R4(Types.toNumber(null));
					estadisticaElement.setLiqCasRestoDoct4(Types.toNumber(null));
					estadisticaElement.setLiqCasRestoT4(Types.toNumber(null));
					estadisticaElement.setLiqCasRestoDocr4(Types.toNumber(null));
					estadisticaElement.setLiqCasRestoR4(Types.toNumber(null));
				} else {
					estadisticaElement.setVerLiqCasino(Types.toStr("S"));
				}
			}
		} else {
			// OTRO MODO
			if (estadisticaElement.getVerLiqCasino().equals("S")) {
				ViewServices.setTabPageVisible("LIQ_CASINOS", true);
			} else {
				ViewServices.setTabPageVisible("LIQ_CASINOS", false);
			}
		}
	}

	/* Original PL/SQL code code for TRIGGER VER_OTROS.WHEN-CHECKBOX-CHANGED
	 COMPRUEBA_INDICADORES('OTROS');
	
	IF :SYSTEM.MODE='NORMAL' THEN
	IF :ESTADISTICA.VER_OTROS='S' THEN
	--SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE REALIZA LA ESTADISTICA DE
	--OTROS CONCEPTOS
	SET_TAB_PAGE_PROPERTY('OTROS',VISIBLE,PROPERTY_TRUE);
	
	CALCULA_ESTADISTICA_LIQ_OTROS;
	ELSE
	--SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE BORRA TODO LO DE OTROS
	--SE PIDE CONFIRMACION
	MOSTRAR_MENSAJE('SUR-02000 #1#2¿Desea borrar la estadística de otros conceptos?','D',FALSE);
	IF NVL(:GLOBAL.QMS$DIALOG_ANSWER,'N')='Y' THEN
	SET_TAB_PAGE_PROPERTY('OTROS',VISIBLE,PROPERTY_FALSE);
	
	:ESTADISTICA.LIQ_OTRO_DOC_G1_COMB:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G1_APUE:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G1_RIFA:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G1_BOLE:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G2_COMB:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G2_APUE:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G2_RIFA:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G2_BOLE:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G3_COMB:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G3_APUE:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G3_RIFA:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G3_BOLE:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G4_COMB:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G4_APUE:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G4_RIFA:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G4_BOLE:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G5_COMB:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G5_APUE:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G5_RIFA:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G5_BOLE:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G6_COMB:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G6_APUE:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G6_RIFA:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_G6_BOLE:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_RESTO_COMB:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_RESTO_APUE:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_RESTO_RIFA:=NULL;
	:ESTADISTICA.LIQ_OTRO_DOC_RESTO_BOLE:=NULL;
	
	:ESTADISTICA.LIQ_OTRO_T_G1_COMB:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G1_APUE:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G1_RIFA:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G1_BOLE:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G2_COMB:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G2_APUE:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G2_RIFA:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G2_BOLE:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G3_COMB:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G3_APUE:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G3_RIFA:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G3_BOLE:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G4_COMB:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G4_APUE:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G4_RIFA:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G4_BOLE:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G5_COMB:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G5_APUE:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G5_RIFA:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G5_BOLE:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G6_COMB:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G6_APUE:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G6_RIFA:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_G6_BOLE:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_RESTO_COMB:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_RESTO_APUE:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_RESTO_RIFA:=NULL;
	:ESTADISTICA.LIQ_OTRO_T_RESTO_BOLE:=NULL;
	
	
	ELSE
	:ESTADISTICA.VER_OTROS:='S';
	END IF;
	END IF;
	ELSE
	IF :ESTADISTICA.VER_OTROS='S' THEN
	SET_TAB_PAGE_PROPERTY('OTROS',VISIBLE,PROPERTY_TRUE);
	ELSE
	SET_TAB_PAGE_PROPERTY('OTROS',VISIBLE,PROPERTY_FALSE);
	END IF;
	END IF;
	
	
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * VER_OTROS.WHEN-CHECKBOX-CHANGED
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "WHEN-CHECKBOX-CHANGED", item = "VER_OTROS")
	public void verOtros_checkBoxChange() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		this.getTask().getServices().compruebaIndicadores(estadisticaElement, Types.toStr("OTROS"));
		if (TaskServices.getMode().equals("NORMAL")) {
			if (estadisticaElement.getVerOtros().equals("S")) {
				// SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE REALIZA LA ESTADISTICA DE
				// OTROS CONCEPTOS
				ViewServices.setTabPageVisible("OTROS", true);
				this.getTask().getServices().calculaEstadisticaLiqOtros(estadisticaElement);
			} else {
				// SI SE CAMBIA A MARCADO (ESTABA NO MARCADO) SE BORRA TODO LO DE OTROS
				// SE PIDE CONFIRMACION
				this.getTask().getServices().mostrarMensaje(Types.toStr("SUR-02000 #1#2¿Desea borrar la estadística de otros conceptos?"), Types.toStr("D"), Types.toBool(NBool.False));
				if (Lib.isNull(Globals.getGlobal("QMS$DIALOG_ANSWER"), "N").equals("Y")) {
					ViewServices.setTabPageVisible("OTROS", false);
					estadisticaElement.setLiqOtroDocG1Comb(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG1Apue(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG1Rifa(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG1Bole(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG2Comb(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG2Apue(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG2Rifa(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG2Bole(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG3Comb(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG3Apue(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG3Rifa(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG3Bole(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG4Comb(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG4Apue(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG4Rifa(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG4Bole(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG5Comb(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG5Apue(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG5Rifa(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG5Bole(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG6Comb(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG6Apue(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG6Rifa(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocG6Bole(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocRestoComb(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocRestoApue(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocRestoRifa(Types.toNumber(null));
					estadisticaElement.setLiqOtroDocRestoBole(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG1Comb(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG1Apue(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG1Rifa(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG1Bole(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG2Comb(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG2Apue(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG2Rifa(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG2Bole(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG3Comb(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG3Apue(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG3Rifa(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG3Bole(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG4Comb(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG4Apue(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG4Rifa(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG4Bole(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG5Comb(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG5Apue(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG5Rifa(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG5Bole(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG6Comb(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG6Apue(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG6Rifa(Types.toNumber(null));
					estadisticaElement.setLiqOtroTG6Bole(Types.toNumber(null));
					estadisticaElement.setLiqOtroTRestoComb(Types.toNumber(null));
					estadisticaElement.setLiqOtroTRestoApue(Types.toNumber(null));
					estadisticaElement.setLiqOtroTRestoRifa(Types.toNumber(null));
					estadisticaElement.setLiqOtroTRestoBole(Types.toNumber(null));
				} else {
					estadisticaElement.setVerOtros(Types.toStr("S"));
				}
			}
		} else {
			if (estadisticaElement.getVerOtros().equals("S")) {
				ViewServices.setTabPageVisible("OTROS", true);
			} else {
				ViewServices.setTabPageVisible("OTROS", false);
			}
		}
	}

	/* Original PL/SQL code code for TRIGGER DOCSTTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.DOCSTTOTAL := (:ESTADISTICA.DOCST1+:ESTADISTICA.DOCST2+:ESTADISTICA.DOCST3+:ESTADISTICA.DOCST4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * DOCSTTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "DOCSTTOTAL")
	public void docsttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setDocsttotal(((estadisticaElement.getDocst1().add(estadisticaElement.getDocst2()).add(estadisticaElement.getDocst3()).add(estadisticaElement.getDocst4()))));
	}

	/* Original PL/SQL code code for TRIGGER TASATOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.TASATOTAL := (:ESTADISTICA.TASA1+:ESTADISTICA.TASA2+:ESTADISTICA.TASA3+:ESTADISTICA.TASA4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * TASATOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "TASATOTAL")
	public void tasatotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setTasatotal(((estadisticaElement.getTasa1().add(estadisticaElement.getTasa2()).add(estadisticaElement.getTasa3()).add(estadisticaElement.getTasa4()))));
	}

	/* Original PL/SQL code code for TRIGGER DOCSRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.DOCSRTOTAL := (:ESTADISTICA.DOCSR1+:ESTADISTICA.DOCSR2+:ESTADISTICA.DOCSR3+:ESTADISTICA.DOCSR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * DOCSRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "DOCSRTOTAL")
	public void docsrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setDocsrtotal(((estadisticaElement.getDocsr1().add(estadisticaElement.getDocsr2()).add(estadisticaElement.getDocsr3()).add(estadisticaElement.getDocsr4()))));
	}

	/* Original PL/SQL code code for TRIGGER RECARGOTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.RECARGOTOTAL := (:ESTADISTICA.RECARGO1+:ESTADISTICA.RECARGO2+:ESTADISTICA.RECARGO3+:ESTADISTICA.RECARGO4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * RECARGOTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "RECARGOTOTAL")
	public void recargototal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setRecargototal(((estadisticaElement.getRecargo1().add(estadisticaElement.getRecargo2()).add(estadisticaElement.getRecargo3()).add(estadisticaElement.getRecargo4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G1_DOCTTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G1_DOCTTOTAL := (:ESTADISTICA.LIQUID_G1_DOCT1+:ESTADISTICA.LIQUID_G1_DOCT2+:ESTADISTICA.LIQUID_G1_DOCT3+:ESTADISTICA.LIQUID_G1_DOCT4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G1_DOCTTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G1_DOCTTOTAL")
	public void liquidG1Docttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG1Docttotal(((estadisticaElement.getLiquidG1Doct1().add(estadisticaElement.getLiquidG1Doct2()).add(estadisticaElement.getLiquidG1Doct3()).add(estadisticaElement.getLiquidG1Doct4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G2_DOCTTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G2_DOCTTOTAL := (:ESTADISTICA.LIQUID_G2_DOCT1+:ESTADISTICA.LIQUID_G2_DOCT2+:ESTADISTICA.LIQUID_G2_DOCT3+:ESTADISTICA.LIQUID_G2_DOCT4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G2_DOCTTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G2_DOCTTOTAL")
	public void liquidG2Docttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG2Docttotal(((estadisticaElement.getLiquidG2Doct1().add(estadisticaElement.getLiquidG2Doct2()).add(estadisticaElement.getLiquidG2Doct3()).add(estadisticaElement.getLiquidG2Doct4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G3_DOCTTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G3_DOCTTOTAL := (:ESTADISTICA.LIQUID_G3_DOCT1+:ESTADISTICA.LIQUID_G3_DOCT2+:ESTADISTICA.LIQUID_G3_DOCT3+:ESTADISTICA.LIQUID_G3_DOCT4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G3_DOCTTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G3_DOCTTOTAL")
	public void liquidG3Docttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG3Docttotal(((estadisticaElement.getLiquidG3Doct1().add(estadisticaElement.getLiquidG3Doct2()).add(estadisticaElement.getLiquidG3Doct3()).add(estadisticaElement.getLiquidG3Doct4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G4_DOCTTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G4_DOCTTOTAL := (:ESTADISTICA.LIQUID_G4_DOCT1+:ESTADISTICA.LIQUID_G4_DOCT2+:ESTADISTICA.LIQUID_G4_DOCT3+:ESTADISTICA.LIQUID_G4_DOCT4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G4_DOCTTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G4_DOCTTOTAL")
	public void liquidG4Docttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG4Docttotal(((estadisticaElement.getLiquidG4Doct1().add(estadisticaElement.getLiquidG4Doct2()).add(estadisticaElement.getLiquidG4Doct3()).add(estadisticaElement.getLiquidG4Doct4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G5_DOCTTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G5_DOCTTOTAL := (:ESTADISTICA.LIQUID_G5_DOCT1+:ESTADISTICA.LIQUID_G5_DOCT2+:ESTADISTICA.LIQUID_G5_DOCT3+:ESTADISTICA.LIQUID_G5_DOCT4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G5_DOCTTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G5_DOCTTOTAL")
	public void liquidG5Docttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG5Docttotal(((estadisticaElement.getLiquidG5Doct1().add(estadisticaElement.getLiquidG5Doct2()).add(estadisticaElement.getLiquidG5Doct3()).add(estadisticaElement.getLiquidG5Doct4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G6_DOCTTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G6_DOCTTOTAL := (:ESTADISTICA.LIQUID_G6_DOCT1+:ESTADISTICA.LIQUID_G6_DOCT2+:ESTADISTICA.LIQUID_G6_DOCT3+:ESTADISTICA.LIQUID_G6_DOCT4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G6_DOCTTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G6_DOCTTOTAL")
	public void liquidG6Docttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG6Docttotal(((estadisticaElement.getLiquidG6Doct1().add(estadisticaElement.getLiquidG6Doct2()).add(estadisticaElement.getLiquidG6Doct3()).add(estadisticaElement.getLiquidG6Doct4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_RESTO_DOCTTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_RESTO_DOCTTOTAL := (:ESTADISTICA.LIQUID_RESTO_DOCT1+:ESTADISTICA.LIQUID_RESTO_DOCT2+:ESTADISTICA.LIQUID_RESTO_DOCT3+:ESTADISTICA.LIQUID_RESTO_DOCT4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_RESTO_DOCTTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_RESTO_DOCTTOTAL")
	public void liquidRestoDocttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidRestoDocttotal(((estadisticaElement.getLiquidRestoDoct1().add(estadisticaElement.getLiquidRestoDoct2()).add(estadisticaElement.getLiquidRestoDoct3()).add(estadisticaElement.getLiquidRestoDoct4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_TOTAL_DOCT1.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_TOTAL_DOCT1 := (:ESTADISTICA.LIQUID_G1_DOCT1+:ESTADISTICA.LIQUID_G2_DOCT1+:ESTADISTICA.LIQUID_G3_DOCT1+:ESTADISTICA.LIQUID_G4_DOCT1+:ESTADISTICA.LIQUID_G5_DOCT1+:ESTADISTICA.LIQUID_RESTO_DOCT1);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_TOTAL_DOCT1.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_TOTAL_DOCT1")
	public void liquidTotalDoct1_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidTotalDoct1(((estadisticaElement.getLiquidG1Doct1().add(estadisticaElement.getLiquidG2Doct1()).add(estadisticaElement.getLiquidG3Doct1()).add(estadisticaElement.getLiquidG4Doct1()).add(estadisticaElement.getLiquidG5Doct1()).add(estadisticaElement.getLiquidRestoDoct1()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_TOTAL_DOCT2.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_TOTAL_DOCT2 := (:ESTADISTICA.LIQUID_G1_DOCT2+:ESTADISTICA.LIQUID_G2_DOCT2+:ESTADISTICA.LIQUID_G3_DOCT2+:ESTADISTICA.LIQUID_G4_DOCT2+:ESTADISTICA.LIQUID_G5_DOCT2+:ESTADISTICA.LIQUID_RESTO_DOCT2);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_TOTAL_DOCT2.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_TOTAL_DOCT2")
	public void liquidTotalDoct2_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidTotalDoct2(((estadisticaElement.getLiquidG1Doct2().add(estadisticaElement.getLiquidG2Doct2()).add(estadisticaElement.getLiquidG3Doct2()).add(estadisticaElement.getLiquidG4Doct2()).add(estadisticaElement.getLiquidG5Doct2()).add(estadisticaElement.getLiquidRestoDoct2()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_TOTAL_DOCT3.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_TOTAL_DOCT3 := (:ESTADISTICA.LIQUID_G1_DOCT3+:ESTADISTICA.LIQUID_G2_DOCT3+:ESTADISTICA.LIQUID_G3_DOCT3+:ESTADISTICA.LIQUID_G4_DOCT3+:ESTADISTICA.LIQUID_G5_DOCT3+:ESTADISTICA.LIQUID_RESTO_DOCT3);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_TOTAL_DOCT3.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_TOTAL_DOCT3")
	public void liquidTotalDoct3_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidTotalDoct3(((estadisticaElement.getLiquidG1Doct3().add(estadisticaElement.getLiquidG2Doct3()).add(estadisticaElement.getLiquidG3Doct3()).add(estadisticaElement.getLiquidG4Doct3()).add(estadisticaElement.getLiquidG5Doct3()).add(estadisticaElement.getLiquidRestoDoct3()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_TOTAL_DOCT4.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_TOTAL_DOCT4 := (:ESTADISTICA.LIQUID_G1_DOCT4+:ESTADISTICA.LIQUID_G2_DOCT4+:ESTADISTICA.LIQUID_G3_DOCT4+:ESTADISTICA.LIQUID_G4_DOCT4+:ESTADISTICA.LIQUID_G5_DOCT4+:ESTADISTICA.LIQUID_RESTO_DOCT4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_TOTAL_DOCT4.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_TOTAL_DOCT4")
	public void liquidTotalDoct4_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidTotalDoct4(((estadisticaElement.getLiquidG1Doct4().add(estadisticaElement.getLiquidG2Doct4()).add(estadisticaElement.getLiquidG3Doct4()).add(estadisticaElement.getLiquidG4Doct4()).add(estadisticaElement.getLiquidG5Doct4()).add(estadisticaElement.getLiquidRestoDoct4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_TOTAL_DOCTTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_TOTAL_DOCTTOTAL := (:ESTADISTICA.LIQUID_TOTAL_DOCT1+:ESTADISTICA.LIQUID_TOTAL_DOCT2+:ESTADISTICA.LIQUID_TOTAL_DOCT3+:ESTADISTICA.LIQUID_TOTAL_DOCT4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_TOTAL_DOCTTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_TOTAL_DOCTTOTAL")
	public void liquidTotalDocttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidTotalDocttotal(((estadisticaElement.getLiquidTotalDoct1().add(estadisticaElement.getLiquidTotalDoct2()).add(estadisticaElement.getLiquidTotalDoct3()).add(estadisticaElement.getLiquidTotalDoct4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G1_TTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G1_TTOTAL := (:ESTADISTICA.LIQUID_G1_T1+:ESTADISTICA.LIQUID_G1_T2+:ESTADISTICA.LIQUID_G1_T3+:ESTADISTICA.LIQUID_G1_T4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G1_TTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G1_TTOTAL")
	public void liquidG1Ttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG1Ttotal(((estadisticaElement.getLiquidG1T1().add(estadisticaElement.getLiquidG1T2()).add(estadisticaElement.getLiquidG1T3()).add(estadisticaElement.getLiquidG1T4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G2_TTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G2_TTOTAL := (:ESTADISTICA.LIQUID_G2_T1+:ESTADISTICA.LIQUID_G2_T2+:ESTADISTICA.LIQUID_G2_T3+:ESTADISTICA.LIQUID_G2_T4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G2_TTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G2_TTOTAL")
	public void liquidG2Ttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG2Ttotal(((estadisticaElement.getLiquidG2T1().add(estadisticaElement.getLiquidG2T2()).add(estadisticaElement.getLiquidG2T3()).add(estadisticaElement.getLiquidG2T4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G3_TTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G3_TTOTAL := (:ESTADISTICA.LIQUID_G3_T1+:ESTADISTICA.LIQUID_G3_T2+:ESTADISTICA.LIQUID_G3_T3+:ESTADISTICA.LIQUID_G3_T4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G3_TTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G3_TTOTAL")
	public void liquidG3Ttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG3Ttotal(((estadisticaElement.getLiquidG3T1().add(estadisticaElement.getLiquidG3T2()).add(estadisticaElement.getLiquidG3T3()).add(estadisticaElement.getLiquidG3T4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G4_TTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G4_TTOTAL := (:ESTADISTICA.LIQUID_G4_T1+:ESTADISTICA.LIQUID_G4_T2+:ESTADISTICA.LIQUID_G4_T3+:ESTADISTICA.LIQUID_G4_T4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G4_TTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G4_TTOTAL")
	public void liquidG4Ttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG4Ttotal(((estadisticaElement.getLiquidG4T1().add(estadisticaElement.getLiquidG4T2()).add(estadisticaElement.getLiquidG4T3()).add(estadisticaElement.getLiquidG4T4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G5_TTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G5_TTOTAL := (:ESTADISTICA.LIQUID_G5_T1+:ESTADISTICA.LIQUID_G5_T2+:ESTADISTICA.LIQUID_G5_T3+:ESTADISTICA.LIQUID_G5_T4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G5_TTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G5_TTOTAL")
	public void liquidG5Ttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG5Ttotal(((estadisticaElement.getLiquidG5T1().add(estadisticaElement.getLiquidG5T2()).add(estadisticaElement.getLiquidG5T3()).add(estadisticaElement.getLiquidG5T4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G6_TTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G6_TTOTAL := (:ESTADISTICA.LIQUID_G6_T1+:ESTADISTICA.LIQUID_G6_T2+:ESTADISTICA.LIQUID_G6_T3+:ESTADISTICA.LIQUID_G6_T4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G6_TTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G6_TTOTAL")
	public void liquidG6Ttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG6Ttotal(((estadisticaElement.getLiquidG6T1().add(estadisticaElement.getLiquidG6T2()).add(estadisticaElement.getLiquidG6T3()).add(estadisticaElement.getLiquidG6T4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_RESTO_TTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_RESTO_TTOTAL := (:ESTADISTICA.LIQUID_RESTO_T1+:ESTADISTICA.LIQUID_RESTO_T2+:ESTADISTICA.LIQUID_RESTO_T3+:ESTADISTICA.LIQUID_RESTO_T4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_RESTO_TTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_RESTO_TTOTAL")
	public void liquidRestoTtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidRestoTtotal(((estadisticaElement.getLiquidRestoT1().add(estadisticaElement.getLiquidRestoT2()).add(estadisticaElement.getLiquidRestoT3()).add(estadisticaElement.getLiquidRestoT4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_TOTAL_T1.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_TOTAL_T1 := (:ESTADISTICA.LIQUID_G1_T1+:ESTADISTICA.LIQUID_G2_T1+:ESTADISTICA.LIQUID_G3_T1+:ESTADISTICA.LIQUID_G4_T1+:ESTADISTICA.LIQUID_G5_T1+:ESTADISTICA.LIQUID_RESTO_T1);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_TOTAL_T1.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_TOTAL_T1")
	public void liquidTotalT1_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidTotalT1(((estadisticaElement.getLiquidG1T1().add(estadisticaElement.getLiquidG2T1()).add(estadisticaElement.getLiquidG3T1()).add(estadisticaElement.getLiquidG4T1()).add(estadisticaElement.getLiquidG5T1()).add(estadisticaElement.getLiquidRestoT1()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_TOTAL_T2.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_TOTAL_T2 := (:ESTADISTICA.LIQUID_G1_T2+:ESTADISTICA.LIQUID_G2_T2+:ESTADISTICA.LIQUID_G3_T2+:ESTADISTICA.LIQUID_G4_T2+:ESTADISTICA.LIQUID_G5_T2+:ESTADISTICA.LIQUID_RESTO_T2);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_TOTAL_T2.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_TOTAL_T2")
	public void liquidTotalT2_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidTotalT2(((estadisticaElement.getLiquidG1T2().add(estadisticaElement.getLiquidG2T2()).add(estadisticaElement.getLiquidG3T2()).add(estadisticaElement.getLiquidG4T2()).add(estadisticaElement.getLiquidG5T2()).add(estadisticaElement.getLiquidRestoT2()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_TOTAL_T3.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_TOTAL_T3 := (:ESTADISTICA.LIQUID_G1_T3+:ESTADISTICA.LIQUID_G2_T3+:ESTADISTICA.LIQUID_G3_T3+:ESTADISTICA.LIQUID_G4_T3+:ESTADISTICA.LIQUID_G5_T3+:ESTADISTICA.LIQUID_RESTO_T3);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_TOTAL_T3.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_TOTAL_T3")
	public void liquidTotalT3_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidTotalT3(((estadisticaElement.getLiquidG1T3().add(estadisticaElement.getLiquidG2T3()).add(estadisticaElement.getLiquidG3T3()).add(estadisticaElement.getLiquidG4T3()).add(estadisticaElement.getLiquidG5T3()).add(estadisticaElement.getLiquidRestoT3()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_TOTAL_T4.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_TOTAL_T4 := (:ESTADISTICA.LIQUID_G1_T4+:ESTADISTICA.LIQUID_G2_T4+:ESTADISTICA.LIQUID_G3_T4+:ESTADISTICA.LIQUID_G4_T4+:ESTADISTICA.LIQUID_G5_T4+:ESTADISTICA.LIQUID_RESTO_T4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_TOTAL_T4.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_TOTAL_T4")
	public void liquidTotalT4_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidTotalT4(((estadisticaElement.getLiquidG1T4().add(estadisticaElement.getLiquidG2T4()).add(estadisticaElement.getLiquidG3T4()).add(estadisticaElement.getLiquidG4T4()).add(estadisticaElement.getLiquidG5T4()).add(estadisticaElement.getLiquidRestoT4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_TOTAL_TTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_TOTAL_TTOTAL := (:ESTADISTICA.LIQUID_TOTAL_T1+:ESTADISTICA.LIQUID_TOTAL_T2+:ESTADISTICA.LIQUID_TOTAL_T3+:ESTADISTICA.LIQUID_TOTAL_T4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_TOTAL_TTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_TOTAL_TTOTAL")
	public void liquidTotalTtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidTotalTtotal(((estadisticaElement.getLiquidTotalT1().add(estadisticaElement.getLiquidTotalT2()).add(estadisticaElement.getLiquidTotalT3()).add(estadisticaElement.getLiquidTotalT4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G1_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G1_DOCRTOTAL := (:ESTADISTICA.LIQUID_G1_DOCR1+:ESTADISTICA.LIQUID_G1_DOCR2+:ESTADISTICA.LIQUID_G1_DOCR3+:ESTADISTICA.LIQUID_G1_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G1_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G1_DOCRTOTAL")
	public void liquidG1Docrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG1Docrtotal(((estadisticaElement.getLiquidG1Docr1().add(estadisticaElement.getLiquidG1Docr2()).add(estadisticaElement.getLiquidG1Docr3()).add(estadisticaElement.getLiquidG1Docr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G2_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G2_DOCRTOTAL := (:ESTADISTICA.LIQUID_G2_DOCR1+:ESTADISTICA.LIQUID_G2_DOCR2+:ESTADISTICA.LIQUID_G2_DOCR3+:ESTADISTICA.LIQUID_G2_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G2_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G2_DOCRTOTAL")
	public void liquidG2Docrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG2Docrtotal(((estadisticaElement.getLiquidG2Docr1().add(estadisticaElement.getLiquidG2Docr2()).add(estadisticaElement.getLiquidG2Docr3()).add(estadisticaElement.getLiquidG2Docr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G3_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G3_DOCRTOTAL := (:ESTADISTICA.LIQUID_G3_DOCR1+:ESTADISTICA.LIQUID_G3_DOCR2+:ESTADISTICA.LIQUID_G3_DOCR3+:ESTADISTICA.LIQUID_G3_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G3_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G3_DOCRTOTAL")
	public void liquidG3Docrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG3Docrtotal(((estadisticaElement.getLiquidG3Docr1().add(estadisticaElement.getLiquidG3Docr2()).add(estadisticaElement.getLiquidG3Docr3()).add(estadisticaElement.getLiquidG3Docr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G4_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G4_DOCRTOTAL := (:ESTADISTICA.LIQUID_G4_DOCR1+:ESTADISTICA.LIQUID_G4_DOCR2+:ESTADISTICA.LIQUID_G4_DOCR3+:ESTADISTICA.LIQUID_G4_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G4_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G4_DOCRTOTAL")
	public void liquidG4Docrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG4Docrtotal(((estadisticaElement.getLiquidG4Docr1().add(estadisticaElement.getLiquidG4Docr2()).add(estadisticaElement.getLiquidG4Docr3()).add(estadisticaElement.getLiquidG4Docr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G5_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G5_DOCRTOTAL := (:ESTADISTICA.LIQUID_G5_DOCR1+:ESTADISTICA.LIQUID_G5_DOCR2+:ESTADISTICA.LIQUID_G5_DOCR3+:ESTADISTICA.LIQUID_G5_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G5_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G5_DOCRTOTAL")
	public void liquidG5Docrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG5Docrtotal(((estadisticaElement.getLiquidG5Docr1().add(estadisticaElement.getLiquidG5Docr2()).add(estadisticaElement.getLiquidG5Docr3()).add(estadisticaElement.getLiquidG5Docr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G6_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G6_DOCRTOTAL := (:ESTADISTICA.LIQUID_G6_DOCR1+:ESTADISTICA.LIQUID_G6_DOCR2+:ESTADISTICA.LIQUID_G6_DOCR3+:ESTADISTICA.LIQUID_G6_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G6_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G6_DOCRTOTAL")
	public void liquidG6Docrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG6Docrtotal(((estadisticaElement.getLiquidG6Docr1().add(estadisticaElement.getLiquidG6Docr2()).add(estadisticaElement.getLiquidG6Docr3()).add(estadisticaElement.getLiquidG6Docr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_RESTO_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_RESTO_DOCRTOTAL := (:ESTADISTICA.LIQUID_RESTO_DOCR1+:ESTADISTICA.LIQUID_RESTO_DOCR2+:ESTADISTICA.LIQUID_RESTO_DOCR3+:ESTADISTICA.LIQUID_RESTO_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_RESTO_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_RESTO_DOCRTOTAL")
	public void liquidRestoDocrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidRestoDocrtotal(((estadisticaElement.getLiquidRestoDocr1().add(estadisticaElement.getLiquidRestoDocr2()).add(estadisticaElement.getLiquidRestoDocr3()).add(estadisticaElement.getLiquidRestoDocr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_TOTAL_DOCR1.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_TOTAL_DOCR1 := (:ESTADISTICA.LIQUID_G1_DOCR1+:ESTADISTICA.LIQUID_G2_DOCR1+:ESTADISTICA.LIQUID_G3_DOCR1+:ESTADISTICA.LIQUID_G4_DOCR1+:ESTADISTICA.LIQUID_G5_DOCR1+:ESTADISTICA.LIQUID_RESTO_DOCR1);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_TOTAL_DOCR1.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_TOTAL_DOCR1")
	public void liquidTotalDocr1_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidTotalDocr1(((estadisticaElement.getLiquidG1Docr1().add(estadisticaElement.getLiquidG2Docr1()).add(estadisticaElement.getLiquidG3Docr1()).add(estadisticaElement.getLiquidG4Docr1()).add(estadisticaElement.getLiquidG5Docr1()).add(estadisticaElement.getLiquidRestoDocr1()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_TOTAL_DOCR2.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_TOTAL_DOCR2 := (:ESTADISTICA.LIQUID_G1_DOCR2+:ESTADISTICA.LIQUID_G2_DOCR2+:ESTADISTICA.LIQUID_G3_DOCR2+:ESTADISTICA.LIQUID_G4_DOCR2+:ESTADISTICA.LIQUID_G5_DOCR2+:ESTADISTICA.LIQUID_RESTO_DOCR2);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_TOTAL_DOCR2.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_TOTAL_DOCR2")
	public void liquidTotalDocr2_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidTotalDocr2(((estadisticaElement.getLiquidG1Docr2().add(estadisticaElement.getLiquidG2Docr2()).add(estadisticaElement.getLiquidG3Docr2()).add(estadisticaElement.getLiquidG4Docr2()).add(estadisticaElement.getLiquidG5Docr2()).add(estadisticaElement.getLiquidRestoDocr2()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_TOTAL_DOCR3.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_TOTAL_DOCR3 := (:ESTADISTICA.LIQUID_G1_DOCR3+:ESTADISTICA.LIQUID_G2_DOCR3+:ESTADISTICA.LIQUID_G3_DOCR3+:ESTADISTICA.LIQUID_G4_DOCR3+:ESTADISTICA.LIQUID_G5_DOCR3+:ESTADISTICA.LIQUID_RESTO_DOCR3);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_TOTAL_DOCR3.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_TOTAL_DOCR3")
	public void liquidTotalDocr3_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidTotalDocr3(((estadisticaElement.getLiquidG1Docr3().add(estadisticaElement.getLiquidG2Docr3()).add(estadisticaElement.getLiquidG3Docr3()).add(estadisticaElement.getLiquidG4Docr3()).add(estadisticaElement.getLiquidG5Docr3()).add(estadisticaElement.getLiquidRestoDocr3()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_TOTAL_DOCR4.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_TOTAL_DOCR4 := (:ESTADISTICA.LIQUID_G1_DOCR4+:ESTADISTICA.LIQUID_G2_DOCR4+:ESTADISTICA.LIQUID_G3_DOCR4+:ESTADISTICA.LIQUID_G4_DOCR4+:ESTADISTICA.LIQUID_G5_DOCR4+:ESTADISTICA.LIQUID_RESTO_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_TOTAL_DOCR4.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_TOTAL_DOCR4")
	public void liquidTotalDocr4_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidTotalDocr4(((estadisticaElement.getLiquidG1Docr4().add(estadisticaElement.getLiquidG2Docr4()).add(estadisticaElement.getLiquidG3Docr4()).add(estadisticaElement.getLiquidG4Docr4()).add(estadisticaElement.getLiquidG5Docr4()).add(estadisticaElement.getLiquidRestoDocr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_TOTAL_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_TOTAL_DOCRTOTAL := (:ESTADISTICA.LIQUID_TOTAL_DOCR1+:ESTADISTICA.LIQUID_TOTAL_DOCR2+:ESTADISTICA.LIQUID_TOTAL_DOCR3+:ESTADISTICA.LIQUID_TOTAL_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_TOTAL_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_TOTAL_DOCRTOTAL")
	public void liquidTotalDocrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidTotalDocrtotal(((estadisticaElement.getLiquidTotalDocr1().add(estadisticaElement.getLiquidTotalDocr2()).add(estadisticaElement.getLiquidTotalDocr3()).add(estadisticaElement.getLiquidTotalDocr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G1_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G1_RTOTAL := (:ESTADISTICA.LIQUID_G1_R1+:ESTADISTICA.LIQUID_G1_R2+:ESTADISTICA.LIQUID_G1_R3+:ESTADISTICA.LIQUID_G1_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G1_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G1_RTOTAL")
	public void liquidG1Rtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG1Rtotal(((estadisticaElement.getLiquidG1R1().add(estadisticaElement.getLiquidG1R2()).add(estadisticaElement.getLiquidG1R3()).add(estadisticaElement.getLiquidG1R4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G2_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G2_RTOTAL := (:ESTADISTICA.LIQUID_G2_R1+:ESTADISTICA.LIQUID_G2_R2+:ESTADISTICA.LIQUID_G2_R3+:ESTADISTICA.LIQUID_G2_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G2_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G2_RTOTAL")
	public void liquidG2Rtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG2Rtotal(((estadisticaElement.getLiquidG2R1().add(estadisticaElement.getLiquidG2R2()).add(estadisticaElement.getLiquidG2R3()).add(estadisticaElement.getLiquidG2R4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G3_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G3_RTOTAL := (:ESTADISTICA.LIQUID_G3_R1+:ESTADISTICA.LIQUID_G3_R2+:ESTADISTICA.LIQUID_G3_R3+:ESTADISTICA.LIQUID_G3_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G3_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G3_RTOTAL")
	public void liquidG3Rtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG3Rtotal(((estadisticaElement.getLiquidG3R1().add(estadisticaElement.getLiquidG3R2()).add(estadisticaElement.getLiquidG3R3()).add(estadisticaElement.getLiquidG3R4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G4_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G4_RTOTAL := (:ESTADISTICA.LIQUID_G4_R1+:ESTADISTICA.LIQUID_G4_R2+:ESTADISTICA.LIQUID_G4_R3+:ESTADISTICA.LIQUID_G4_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G4_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G4_RTOTAL")
	public void liquidG4Rtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG4Rtotal(((estadisticaElement.getLiquidG4R1().add(estadisticaElement.getLiquidG4R2()).add(estadisticaElement.getLiquidG4R3()).add(estadisticaElement.getLiquidG4R4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G5_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G5_RTOTAL := (:ESTADISTICA.LIQUID_G5_R1+:ESTADISTICA.LIQUID_G5_R2+:ESTADISTICA.LIQUID_G5_R3+:ESTADISTICA.LIQUID_G5_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G5_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G5_RTOTAL")
	public void liquidG5Rtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG5Rtotal(((estadisticaElement.getLiquidG5R1().add(estadisticaElement.getLiquidG5R2()).add(estadisticaElement.getLiquidG5R3()).add(estadisticaElement.getLiquidG5R4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_G6_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_G6_RTOTAL := (:ESTADISTICA.LIQUID_G6_R1+:ESTADISTICA.LIQUID_G6_R2+:ESTADISTICA.LIQUID_G6_R3+:ESTADISTICA.LIQUID_G6_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_G6_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_G6_RTOTAL")
	public void liquidG6Rtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidG6Rtotal(((estadisticaElement.getLiquidG6R1().add(estadisticaElement.getLiquidG6R2()).add(estadisticaElement.getLiquidG6R3()).add(estadisticaElement.getLiquidG6R4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_RESTO_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_RESTO_RTOTAL := (:ESTADISTICA.LIQUID_RESTO_R1+:ESTADISTICA.LIQUID_RESTO_R2+:ESTADISTICA.LIQUID_RESTO_R3+:ESTADISTICA.LIQUID_RESTO_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_RESTO_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_RESTO_RTOTAL")
	public void liquidRestoRtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidRestoRtotal(((estadisticaElement.getLiquidRestoR1().add(estadisticaElement.getLiquidRestoR2()).add(estadisticaElement.getLiquidRestoR3()).add(estadisticaElement.getLiquidRestoR4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_TOTAL_R1.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_TOTAL_R1 := (:ESTADISTICA.LIQUID_G1_R1+:ESTADISTICA.LIQUID_G2_R1+:ESTADISTICA.LIQUID_G3_R1+:ESTADISTICA.LIQUID_G4_R1+:ESTADISTICA.LIQUID_G5_R1+:ESTADISTICA.LIQUID_RESTO_R1);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_TOTAL_R1.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_TOTAL_R1")
	public void liquidTotalR1_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidTotalR1(((estadisticaElement.getLiquidG1R1().add(estadisticaElement.getLiquidG2R1()).add(estadisticaElement.getLiquidG3R1()).add(estadisticaElement.getLiquidG4R1()).add(estadisticaElement.getLiquidG5R1()).add(estadisticaElement.getLiquidRestoR1()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_TOTAL_R2.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_TOTAL_R2 := (:ESTADISTICA.LIQUID_G1_R2+:ESTADISTICA.LIQUID_G2_R2+:ESTADISTICA.LIQUID_G3_R2+:ESTADISTICA.LIQUID_G4_R2+:ESTADISTICA.LIQUID_G5_R2+:ESTADISTICA.LIQUID_RESTO_R2);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_TOTAL_R2.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_TOTAL_R2")
	public void liquidTotalR2_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidTotalR2(((estadisticaElement.getLiquidG1R2().add(estadisticaElement.getLiquidG2R2()).add(estadisticaElement.getLiquidG3R2()).add(estadisticaElement.getLiquidG4R2()).add(estadisticaElement.getLiquidG5R2()).add(estadisticaElement.getLiquidRestoR2()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_TOTAL_R3.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_TOTAL_R3 := (:ESTADISTICA.LIQUID_G1_R3+:ESTADISTICA.LIQUID_G2_R3+:ESTADISTICA.LIQUID_G3_R3+:ESTADISTICA.LIQUID_G4_R3+:ESTADISTICA.LIQUID_G5_R3+:ESTADISTICA.LIQUID_RESTO_R3);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_TOTAL_R3.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_TOTAL_R3")
	public void liquidTotalR3_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidTotalR3(((estadisticaElement.getLiquidG1R3().add(estadisticaElement.getLiquidG2R3()).add(estadisticaElement.getLiquidG3R3()).add(estadisticaElement.getLiquidG4R3()).add(estadisticaElement.getLiquidG5R3()).add(estadisticaElement.getLiquidRestoR3()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_TOTAL_R4.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_TOTAL_R4 := (:ESTADISTICA.LIQUID_G1_R4+:ESTADISTICA.LIQUID_G2_R4+:ESTADISTICA.LIQUID_G3_R4+:ESTADISTICA.LIQUID_G4_R4+:ESTADISTICA.LIQUID_G5_R4+:ESTADISTICA.LIQUID_RESTO_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_TOTAL_R4.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_TOTAL_R4")
	public void liquidTotalR4_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidTotalR4(((estadisticaElement.getLiquidG1R4().add(estadisticaElement.getLiquidG2R4()).add(estadisticaElement.getLiquidG3R4()).add(estadisticaElement.getLiquidG4R4()).add(estadisticaElement.getLiquidG5R4()).add(estadisticaElement.getLiquidRestoR4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQUID_TOTAL_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQUID_TOTAL_RTOTAL := (:ESTADISTICA.LIQUID_TOTAL_R1+:ESTADISTICA.LIQUID_TOTAL_R2+:ESTADISTICA.LIQUID_TOTAL_R3+:ESTADISTICA.LIQUID_TOTAL_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQUID_TOTAL_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQUID_TOTAL_RTOTAL")
	public void liquidTotalRtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiquidTotalRtotal(((estadisticaElement.getLiquidTotalR1().add(estadisticaElement.getLiquidTotalR2()).add(estadisticaElement.getLiquidTotalR3()).add(estadisticaElement.getLiquidTotalR4()))));
	}

	/* Original PL/SQL code code for TRIGGER RB_LIQ_PERIODO.WHEN-RADIO-CHANGED
	 VISUALIZACION_LIQUID;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * RB_LIQ_PERIODO.WHEN-RADIO-CHANGED
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "WHEN-RADIO-CHANGED", item = "RB_LIQ_PERIODO")
	public void rbLiqPeriodo_radioGroupChange() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		this.getTask().getServices().visualizacionLiquid(estadisticaElement);
	}

	/* Original PL/SQL code code for TRIGGER BINGO_DOCT1_TOTALT.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_DOCT1_TOTALT := (:ESTADISTICA.BINGO_DOCT1_1T+:ESTADISTICA.BINGO_DOCT1_2T+:ESTADISTICA.BINGO_DOCT1_3T+:ESTADISTICA.BINGO_DOCT1_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_DOCT1_TOTALT.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_DOCT1_TOTALT")
	public void bingoDoct1Totalt_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoDoct1Totalt(((estadisticaElement.getBingoDoct11t().add(estadisticaElement.getBingoDoct12t()).add(estadisticaElement.getBingoDoct13t()).add(estadisticaElement.getBingoDoct14t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_DOCT2_TOTALT.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_DOCT2_TOTALT := (:ESTADISTICA.BINGO_DOCT2_1T+:ESTADISTICA.BINGO_DOCT2_2T+:ESTADISTICA.BINGO_DOCT2_3T+:ESTADISTICA.BINGO_DOCT2_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_DOCT2_TOTALT.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_DOCT2_TOTALT")
	public void bingoDoct2Totalt_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoDoct2Totalt(((estadisticaElement.getBingoDoct21t().add(estadisticaElement.getBingoDoct22t()).add(estadisticaElement.getBingoDoct23t()).add(estadisticaElement.getBingoDoct24t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_DOCT3_TOTALT.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_DOCT3_TOTALT := (:ESTADISTICA.BINGO_DOCT3_1T+:ESTADISTICA.BINGO_DOCT3_2T+:ESTADISTICA.BINGO_DOCT3_3T+:ESTADISTICA.BINGO_DOCT3_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_DOCT3_TOTALT.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_DOCT3_TOTALT")
	public void bingoDoct3Totalt_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoDoct3Totalt(((estadisticaElement.getBingoDoct31t().add(estadisticaElement.getBingoDoct32t()).add(estadisticaElement.getBingoDoct33t()).add(estadisticaElement.getBingoDoct34t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_DOCT4_TOTALT.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_DOCT4_TOTALT := (:ESTADISTICA.BINGO_DOCT4_1T+:ESTADISTICA.BINGO_DOCT4_2T+:ESTADISTICA.BINGO_DOCT4_3T+:ESTADISTICA.BINGO_DOCT4_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_DOCT4_TOTALT.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_DOCT4_TOTALT")
	public void bingoDoct4Totalt_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoDoct4Totalt(((estadisticaElement.getBingoDoct41t().add(estadisticaElement.getBingoDoct42t()).add(estadisticaElement.getBingoDoct43t()).add(estadisticaElement.getBingoDoct44t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_DOCTTOTAL_1T.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_DOCTTOTAL_1T := (:ESTADISTICA.BINGO_DOCT1_1T+:ESTADISTICA.BINGO_DOCT2_1T+:ESTADISTICA.BINGO_DOCT3_1T+:ESTADISTICA.BINGO_DOCT4_1T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_DOCTTOTAL_1T.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_DOCTTOTAL_1T")
	public void bingoDocttotal1t_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoDocttotal1t(((estadisticaElement.getBingoDoct11t().add(estadisticaElement.getBingoDoct21t()).add(estadisticaElement.getBingoDoct31t()).add(estadisticaElement.getBingoDoct41t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_DOCTTOTAL_2T.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_DOCTTOTAL_2T := (:ESTADISTICA.BINGO_DOCT1_2T+:ESTADISTICA.BINGO_DOCT2_2T+:ESTADISTICA.BINGO_DOCT3_2T+:ESTADISTICA.BINGO_DOCT4_2T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_DOCTTOTAL_2T.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_DOCTTOTAL_2T")
	public void bingoDocttotal2t_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoDocttotal2t(((estadisticaElement.getBingoDoct12t().add(estadisticaElement.getBingoDoct22t()).add(estadisticaElement.getBingoDoct32t()).add(estadisticaElement.getBingoDoct42t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_DOCTTOTAL_3T.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_DOCTTOTAL_3T := (:ESTADISTICA.BINGO_DOCT1_3T+:ESTADISTICA.BINGO_DOCT2_3T+:ESTADISTICA.BINGO_DOCT3_3T+:ESTADISTICA.BINGO_DOCT4_3T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_DOCTTOTAL_3T.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_DOCTTOTAL_3T")
	public void bingoDocttotal3t_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoDocttotal3t(((estadisticaElement.getBingoDoct13t().add(estadisticaElement.getBingoDoct23t()).add(estadisticaElement.getBingoDoct33t()).add(estadisticaElement.getBingoDoct43t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_DOCTTOTAL_4T.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_DOCTTOTAL_4T := (:ESTADISTICA.BINGO_DOCT1_4T+:ESTADISTICA.BINGO_DOCT2_4T+:ESTADISTICA.BINGO_DOCT3_4T+:ESTADISTICA.BINGO_DOCT4_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_DOCTTOTAL_4T.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_DOCTTOTAL_4T")
	public void bingoDocttotal4t_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoDocttotal4t(((estadisticaElement.getBingoDoct14t().add(estadisticaElement.getBingoDoct24t()).add(estadisticaElement.getBingoDoct34t()).add(estadisticaElement.getBingoDoct44t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_DOCTTOTAL_TOTALT.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_DOCTTOTAL_TOTALT := (:ESTADISTICA.BINGO_DOCTTOTAL_1T+:ESTADISTICA.BINGO_DOCTTOTAL_2T+:ESTADISTICA.BINGO_DOCTTOTAL_3T+:ESTADISTICA.BINGO_DOCTTOTAL_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_DOCTTOTAL_TOTALT.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_DOCTTOTAL_TOTALT")
	public void bingoDocttotalTotalt_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoDocttotalTotalt(((estadisticaElement.getBingoDocttotal1t().add(estadisticaElement.getBingoDocttotal2t()).add(estadisticaElement.getBingoDocttotal3t()).add(estadisticaElement.getBingoDocttotal4t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_B1_TOTALT.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_B1_TOTALT := (:ESTADISTICA.BINGO_B1_1T+:ESTADISTICA.BINGO_B1_2T+:ESTADISTICA.BINGO_B1_3T+:ESTADISTICA.BINGO_B1_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_B1_TOTALT.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_B1_TOTALT")
	public void bingoB1Totalt_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoB1Totalt(((estadisticaElement.getBingoB11t().add(estadisticaElement.getBingoB12t()).add(estadisticaElement.getBingoB13t()).add(estadisticaElement.getBingoB14t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_B2_TOTALT.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_B2_TOTALT := (:ESTADISTICA.BINGO_B2_1T+:ESTADISTICA.BINGO_B2_2T+:ESTADISTICA.BINGO_B2_3T+:ESTADISTICA.BINGO_B2_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_B2_TOTALT.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_B2_TOTALT")
	public void bingoB2Totalt_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoB2Totalt(((estadisticaElement.getBingoB21t().add(estadisticaElement.getBingoB22t()).add(estadisticaElement.getBingoB23t()).add(estadisticaElement.getBingoB24t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_B3_TOTALT.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_B3_TOTALT := (:ESTADISTICA.BINGO_B3_1T+:ESTADISTICA.BINGO_B3_2T+:ESTADISTICA.BINGO_B3_3T+:ESTADISTICA.BINGO_B3_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_B3_TOTALT.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_B3_TOTALT")
	public void bingoB3Totalt_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoB3Totalt(((estadisticaElement.getBingoB31t().add(estadisticaElement.getBingoB32t()).add(estadisticaElement.getBingoB33t()).add(estadisticaElement.getBingoB34t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_B4_TOTALT.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_B4_TOTALT := (:ESTADISTICA.BINGO_B4_1T+:ESTADISTICA.BINGO_B4_2T+:ESTADISTICA.BINGO_B4_3T+:ESTADISTICA.BINGO_B4_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_B4_TOTALT.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_B4_TOTALT")
	public void bingoB4Totalt_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoB4Totalt(((estadisticaElement.getBingoB41t().add(estadisticaElement.getBingoB42t()).add(estadisticaElement.getBingoB43t()).add(estadisticaElement.getBingoB44t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_BTOTAL_1T.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_BTOTAL_1T := (:ESTADISTICA.BINGO_B1_1T+:ESTADISTICA.BINGO_B2_1T+:ESTADISTICA.BINGO_B3_1T+:ESTADISTICA.BINGO_B4_1T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_BTOTAL_1T.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_BTOTAL_1T")
	public void bingoBtotal1t_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoBtotal1t(((estadisticaElement.getBingoB11t().add(estadisticaElement.getBingoB21t()).add(estadisticaElement.getBingoB31t()).add(estadisticaElement.getBingoB41t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_BTOTAL_2T.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_BTOTAL_2T := (:ESTADISTICA.BINGO_B1_2T+:ESTADISTICA.BINGO_B2_2T+:ESTADISTICA.BINGO_B3_2T+:ESTADISTICA.BINGO_B4_2T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_BTOTAL_2T.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_BTOTAL_2T")
	public void bingoBtotal2t_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoBtotal2t(((estadisticaElement.getBingoB12t().add(estadisticaElement.getBingoB22t()).add(estadisticaElement.getBingoB32t()).add(estadisticaElement.getBingoB42t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_BTOTAL_3T.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_BTOTAL_3T := (:ESTADISTICA.BINGO_B1_3T+:ESTADISTICA.BINGO_B2_3T+:ESTADISTICA.BINGO_B3_3T+:ESTADISTICA.BINGO_B4_3T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_BTOTAL_3T.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_BTOTAL_3T")
	public void bingoBtotal3t_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoBtotal3t(((estadisticaElement.getBingoB13t().add(estadisticaElement.getBingoB23t()).add(estadisticaElement.getBingoB33t()).add(estadisticaElement.getBingoB43t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_BTOTAL_4T.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_BTOTAL_4T := (:ESTADISTICA.BINGO_B1_4T+:ESTADISTICA.BINGO_B2_4T+:ESTADISTICA.BINGO_B3_4T+:ESTADISTICA.BINGO_B4_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_BTOTAL_4T.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_BTOTAL_4T")
	public void bingoBtotal4t_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoBtotal4t(((estadisticaElement.getBingoB14t().add(estadisticaElement.getBingoB24t()).add(estadisticaElement.getBingoB34t()).add(estadisticaElement.getBingoB44t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_BTOTAL_TOTALT.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_BTOTAL_TOTALT := (:ESTADISTICA.BINGO_BTOTAL_1T+:ESTADISTICA.BINGO_BTOTAL_2T+:ESTADISTICA.BINGO_BTOTAL_3T+:ESTADISTICA.BINGO_BTOTAL_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_BTOTAL_TOTALT.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_BTOTAL_TOTALT")
	public void bingoBtotalTotalt_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoBtotalTotalt(((estadisticaElement.getBingoBtotal1t().add(estadisticaElement.getBingoBtotal2t()).add(estadisticaElement.getBingoBtotal3t()).add(estadisticaElement.getBingoBtotal4t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_T1_TOTALT.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_T1_TOTALT := (:ESTADISTICA.BINGO_T1_1T+:ESTADISTICA.BINGO_T1_2T+:ESTADISTICA.BINGO_T1_3T+:ESTADISTICA.BINGO_T1_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_T1_TOTALT.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_T1_TOTALT")
	public void bingoT1Totalt_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoT1Totalt(((estadisticaElement.getBingoT11t().add(estadisticaElement.getBingoT12t()).add(estadisticaElement.getBingoT13t()).add(estadisticaElement.getBingoT14t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_T2_TOTALT.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_T2_TOTALT := (:ESTADISTICA.BINGO_T2_1T+:ESTADISTICA.BINGO_T2_2T+:ESTADISTICA.BINGO_T2_3T+:ESTADISTICA.BINGO_T2_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_T2_TOTALT.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_T2_TOTALT")
	public void bingoT2Totalt_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoT2Totalt(((estadisticaElement.getBingoT21t().add(estadisticaElement.getBingoT22t()).add(estadisticaElement.getBingoT23t()).add(estadisticaElement.getBingoT24t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_T3_TOTALT.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_T3_TOTALT := (:ESTADISTICA.BINGO_T3_1T+:ESTADISTICA.BINGO_T3_2T+:ESTADISTICA.BINGO_T3_3T+:ESTADISTICA.BINGO_T3_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_T3_TOTALT.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_T3_TOTALT")
	public void bingoT3Totalt_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoT3Totalt(((estadisticaElement.getBingoT31t().add(estadisticaElement.getBingoT32t()).add(estadisticaElement.getBingoT33t()).add(estadisticaElement.getBingoT34t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_T4_TOTALT.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_T4_TOTALT := (:ESTADISTICA.BINGO_T4_1T+:ESTADISTICA.BINGO_T4_2T+:ESTADISTICA.BINGO_T4_3T+:ESTADISTICA.BINGO_T4_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_T4_TOTALT.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_T4_TOTALT")
	public void bingoT4Totalt_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoT4Totalt(((estadisticaElement.getBingoT41t().add(estadisticaElement.getBingoT42t()).add(estadisticaElement.getBingoT43t()).add(estadisticaElement.getBingoT44t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_TTOTAL_1T.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_TTOTAL_1T := (:ESTADISTICA.BINGO_T1_1T+:ESTADISTICA.BINGO_T2_1T+:ESTADISTICA.BINGO_T3_1T+:ESTADISTICA.BINGO_T4_1T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_TTOTAL_1T.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_TTOTAL_1T")
	public void bingoTtotal1t_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoTtotal1t(((estadisticaElement.getBingoT11t().add(estadisticaElement.getBingoT21t()).add(estadisticaElement.getBingoT31t()).add(estadisticaElement.getBingoT41t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_TTOTAL_2T.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_TTOTAL_2T := (:ESTADISTICA.BINGO_T1_2T+:ESTADISTICA.BINGO_T2_2T+:ESTADISTICA.BINGO_T3_2T+:ESTADISTICA.BINGO_T4_2T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_TTOTAL_2T.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_TTOTAL_2T")
	public void bingoTtotal2t_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoTtotal2t(((estadisticaElement.getBingoT12t().add(estadisticaElement.getBingoT22t()).add(estadisticaElement.getBingoT32t()).add(estadisticaElement.getBingoT42t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_TTOTAL_3T.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_TTOTAL_3T := (:ESTADISTICA.BINGO_T1_3T+:ESTADISTICA.BINGO_T2_3T+:ESTADISTICA.BINGO_T3_3T+:ESTADISTICA.BINGO_T4_3T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_TTOTAL_3T.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_TTOTAL_3T")
	public void bingoTtotal3t_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoTtotal3t(((estadisticaElement.getBingoT13t().add(estadisticaElement.getBingoT23t()).add(estadisticaElement.getBingoT33t()).add(estadisticaElement.getBingoT43t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_TTOTAL_4T.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_TTOTAL_4T := (:ESTADISTICA.BINGO_T1_4T+:ESTADISTICA.BINGO_T2_4T+:ESTADISTICA.BINGO_T3_4T+:ESTADISTICA.BINGO_T4_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_TTOTAL_4T.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_TTOTAL_4T")
	public void bingoTtotal4t_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoTtotal4t(((estadisticaElement.getBingoT14t().add(estadisticaElement.getBingoT24t()).add(estadisticaElement.getBingoT34t()).add(estadisticaElement.getBingoT44t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_TTOTAL_TOTALT.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_TTOTAL_TOTALT := (:ESTADISTICA.BINGO_TTOTAL_1T+:ESTADISTICA.BINGO_TTOTAL_2T+:ESTADISTICA.BINGO_TTOTAL_3T+:ESTADISTICA.BINGO_TTOTAL_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_TTOTAL_TOTALT.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_TTOTAL_TOTALT")
	public void bingoTtotalTotalt_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoTtotalTotalt(((estadisticaElement.getBingoTtotal1t().add(estadisticaElement.getBingoTtotal2t()).add(estadisticaElement.getBingoTtotal3t()).add(estadisticaElement.getBingoTtotal4t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_R1_TOTALT.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_R1_TOTALT := (:ESTADISTICA.BINGO_R1_1T+:ESTADISTICA.BINGO_R1_2T+:ESTADISTICA.BINGO_R1_3T+:ESTADISTICA.BINGO_R1_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_R1_TOTALT.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_R1_TOTALT")
	public void bingoR1Totalt_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoR1Totalt(((estadisticaElement.getBingoR11t().add(estadisticaElement.getBingoR12t()).add(estadisticaElement.getBingoR13t()).add(estadisticaElement.getBingoR14t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_R2_TOTALT.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_R2_TOTALT := (:ESTADISTICA.BINGO_R2_1T+:ESTADISTICA.BINGO_R2_2T+:ESTADISTICA.BINGO_R2_3T+:ESTADISTICA.BINGO_R2_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_R2_TOTALT.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_R2_TOTALT")
	public void bingoR2Totalt_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoR2Totalt(((estadisticaElement.getBingoR21t().add(estadisticaElement.getBingoR22t()).add(estadisticaElement.getBingoR23t()).add(estadisticaElement.getBingoR24t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_R3_TOTALT.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_R3_TOTALT := (:ESTADISTICA.BINGO_R3_1T+:ESTADISTICA.BINGO_R3_2T+:ESTADISTICA.BINGO_R3_3T+:ESTADISTICA.BINGO_R3_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_R3_TOTALT.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_R3_TOTALT")
	public void bingoR3Totalt_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoR3Totalt(((estadisticaElement.getBingoR31t().add(estadisticaElement.getBingoR32t()).add(estadisticaElement.getBingoR33t()).add(estadisticaElement.getBingoR34t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_R4_TOTALT.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_R4_TOTALT := (:ESTADISTICA.BINGO_R4_1T+:ESTADISTICA.BINGO_R4_2T+:ESTADISTICA.BINGO_R4_3T+:ESTADISTICA.BINGO_R4_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_R4_TOTALT.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_R4_TOTALT")
	public void bingoR4Totalt_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoR4Totalt(((estadisticaElement.getBingoR41t().add(estadisticaElement.getBingoR42t()).add(estadisticaElement.getBingoR43t()).add(estadisticaElement.getBingoR44t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_RTOTAL_1T.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_RTOTAL_1T := (:ESTADISTICA.BINGO_R1_1T+:ESTADISTICA.BINGO_R2_1T+:ESTADISTICA.BINGO_R3_1T+:ESTADISTICA.BINGO_R4_1T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_RTOTAL_1T.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_RTOTAL_1T")
	public void bingoRtotal1t_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoRtotal1t(((estadisticaElement.getBingoR11t().add(estadisticaElement.getBingoR21t()).add(estadisticaElement.getBingoR31t()).add(estadisticaElement.getBingoR41t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_RTOTAL_2T.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_RTOTAL_2T := (:ESTADISTICA.BINGO_R1_2T+:ESTADISTICA.BINGO_R2_2T+:ESTADISTICA.BINGO_R3_2T+:ESTADISTICA.BINGO_R4_2T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_RTOTAL_2T.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_RTOTAL_2T")
	public void bingoRtotal2t_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoRtotal2t(((estadisticaElement.getBingoR12t().add(estadisticaElement.getBingoR22t()).add(estadisticaElement.getBingoR32t()).add(estadisticaElement.getBingoR42t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_RTOTAL_3T.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_RTOTAL_3T := (:ESTADISTICA.BINGO_R1_3T+:ESTADISTICA.BINGO_R2_3T+:ESTADISTICA.BINGO_R3_3T+:ESTADISTICA.BINGO_R4_3T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_RTOTAL_3T.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_RTOTAL_3T")
	public void bingoRtotal3t_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoRtotal3t(((estadisticaElement.getBingoR13t().add(estadisticaElement.getBingoR23t()).add(estadisticaElement.getBingoR33t()).add(estadisticaElement.getBingoR43t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_RTOTAL_4T.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_RTOTAL_4T := (:ESTADISTICA.BINGO_R1_4T+:ESTADISTICA.BINGO_R2_4T+:ESTADISTICA.BINGO_R3_4T+:ESTADISTICA.BINGO_R4_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_RTOTAL_4T.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_RTOTAL_4T")
	public void bingoRtotal4t_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoRtotal4t(((estadisticaElement.getBingoR14t().add(estadisticaElement.getBingoR24t()).add(estadisticaElement.getBingoR34t()).add(estadisticaElement.getBingoR44t()))));
	}

	/* Original PL/SQL code code for TRIGGER BINGO_RTOTAL_TOTALT.FORMULA-CALCULATION
	begin
	:ESTADISTICA.BINGO_RTOTAL_TOTALT := (:ESTADISTICA.BINGO_RTOTAL_1T+:ESTADISTICA.BINGO_RTOTAL_2T+:ESTADISTICA.BINGO_RTOTAL_3T+:ESTADISTICA.BINGO_RTOTAL_4T);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * BINGO_RTOTAL_TOTALT.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "BINGO_RTOTAL_TOTALT")
	public void bingoRtotalTotalt_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setBingoRtotalTotalt(((estadisticaElement.getBingoRtotal1t().add(estadisticaElement.getBingoRtotal2t()).add(estadisticaElement.getBingoRtotal3t()).add(estadisticaElement.getBingoRtotal4t()))));
	}

	/* Original PL/SQL code code for TRIGGER RB_BINGO_PERIODO.WHEN-RADIO-CHANGED
	 VISUALIZACION_BINGO;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * RB_BINGO_PERIODO.WHEN-RADIO-CHANGED
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "WHEN-RADIO-CHANGED", item = "RB_BINGO_PERIODO")
	public void rbBingoPeriodo_radioGroupChange() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		this.getTask().getServices().visualizacionBingo(estadisticaElement);
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_TOTAL_DOCT_TOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_TOTAL_DOCT_TOTAL := (:ESTADISTICA.LIQ_BIN_G1_DOCT_TOTAL+:ESTADISTICA.LIQ_BIN_G2_DOCT_TOTAL+:ESTADISTICA.LIQ_BIN_G3_DOCT_TOTAL+:ESTADISTICA.LIQ_BIN_G4_DOCT_TOTAL+:ESTADISTICA.LIQ_BIN_G5_DOCT_TOTAL+:ESTADISTICA.LIQ_BIN_RESTO_DOCT_TOTAL);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_TOTAL_DOCT_TOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_TOTAL_DOCT_TOTAL")
	public void liqBinTotalDoctTotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinTotalDoctTotal(((estadisticaElement.getLiqBinG1DoctTotal().add(estadisticaElement.getLiqBinG2DoctTotal()).add(estadisticaElement.getLiqBinG3DoctTotal()).add(estadisticaElement.getLiqBinG4DoctTotal()).add(estadisticaElement.getLiqBinG5DoctTotal()).add(estadisticaElement.getLiqBinRestoDoctTotal()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_TOTAL_T_TOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_TOTAL_T_TOTAL := (+:ESTADISTICA.LIQ_BIN_G2_T_TOTAL+:ESTADISTICA.LIQ_BIN_G3_T_TOTAL+:ESTADISTICA.LIQ_BIN_G4_T_TOTAL+:ESTADISTICA.LIQ_BIN_G5_T_TOTAL+:ESTADISTICA.LIQ_BIN_RESTO_T_TOTAL);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_TOTAL_T_TOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_TOTAL_T_TOTAL")
	public void liqBinTotalTTotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinTotalTTotal((((estadisticaElement.getLiqBinG2TTotal()).add(estadisticaElement.getLiqBinG3TTotal()).add(estadisticaElement.getLiqBinG4TTotal()).add(estadisticaElement.getLiqBinG5TTotal()).add(estadisticaElement.getLiqBinRestoTTotal()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_G1_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_G1_DOCRTOTAL := (:ESTADISTICA.LIQ_BIN_G1_DOCR1+:ESTADISTICA.LIQ_BIN_G1_DOCR2+:ESTADISTICA.LIQ_BIN_G1_DOCR3+:ESTADISTICA.LIQ_BIN_G1_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_G1_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_G1_DOCRTOTAL")
	public void liqBinG1Docrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinG1Docrtotal(((estadisticaElement.getLiqBinG1Docr1().add(estadisticaElement.getLiqBinG1Docr2()).add(estadisticaElement.getLiqBinG1Docr3()).add(estadisticaElement.getLiqBinG1Docr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_G2_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_G2_DOCRTOTAL := (:ESTADISTICA.LIQ_BIN_G2_DOCR1+:ESTADISTICA.LIQ_BIN_G2_DOCR2+:ESTADISTICA.LIQ_BIN_G2_DOCR3+:ESTADISTICA.LIQ_BIN_G2_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_G2_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_G2_DOCRTOTAL")
	public void liqBinG2Docrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinG2Docrtotal(((estadisticaElement.getLiqBinG2Docr1().add(estadisticaElement.getLiqBinG2Docr2()).add(estadisticaElement.getLiqBinG2Docr3()).add(estadisticaElement.getLiqBinG2Docr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_G3_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_G3_DOCRTOTAL := (:ESTADISTICA.LIQ_BIN_G3_DOCR1+:ESTADISTICA.LIQ_BIN_G3_DOCR2+:ESTADISTICA.LIQ_BIN_G3_DOCR3+:ESTADISTICA.LIQ_BIN_G3_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_G3_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_G3_DOCRTOTAL")
	public void liqBinG3Docrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinG3Docrtotal(((estadisticaElement.getLiqBinG3Docr1().add(estadisticaElement.getLiqBinG3Docr2()).add(estadisticaElement.getLiqBinG3Docr3()).add(estadisticaElement.getLiqBinG3Docr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_G4_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_G4_DOCRTOTAL := (:ESTADISTICA.LIQ_BIN_G4_DOCR1+:ESTADISTICA.LIQ_BIN_G4_DOCR2+:ESTADISTICA.LIQ_BIN_G4_DOCR3+:ESTADISTICA.LIQ_BIN_G4_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_G4_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_G4_DOCRTOTAL")
	public void liqBinG4Docrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinG4Docrtotal(((estadisticaElement.getLiqBinG4Docr1().add(estadisticaElement.getLiqBinG4Docr2()).add(estadisticaElement.getLiqBinG4Docr3()).add(estadisticaElement.getLiqBinG4Docr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_G5_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_G5_DOCRTOTAL := (:ESTADISTICA.LIQ_BIN_G5_DOCR1+:ESTADISTICA.LIQ_BIN_G5_DOCR2+:ESTADISTICA.LIQ_BIN_G5_DOCR3+:ESTADISTICA.LIQ_BIN_G5_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_G5_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_G5_DOCRTOTAL")
	public void liqBinG5Docrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinG5Docrtotal(((estadisticaElement.getLiqBinG5Docr1().add(estadisticaElement.getLiqBinG5Docr2()).add(estadisticaElement.getLiqBinG5Docr3()).add(estadisticaElement.getLiqBinG5Docr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_G6_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_G6_DOCRTOTAL := (:ESTADISTICA.LIQ_BIN_G6_DOCR1+:ESTADISTICA.LIQ_BIN_G6_DOCR2+:ESTADISTICA.LIQ_BIN_G6_DOCR3+:ESTADISTICA.LIQ_BIN_G6_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_G6_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_G6_DOCRTOTAL")
	public void liqBinG6Docrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinG6Docrtotal(((estadisticaElement.getLiqBinG6Docr1().add(estadisticaElement.getLiqBinG6Docr2()).add(estadisticaElement.getLiqBinG6Docr3()).add(estadisticaElement.getLiqBinG6Docr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_RESTO_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_RESTO_DOCRTOTAL := (:ESTADISTICA.LIQ_BIN_RESTO_DOCR1+:ESTADISTICA.LIQ_BIN_RESTO_DOCR2+:ESTADISTICA.LIQ_BIN_RESTO_DOCR3+:ESTADISTICA.LIQ_BIN_RESTO_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_RESTO_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_RESTO_DOCRTOTAL")
	public void liqBinRestoDocrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinRestoDocrtotal(((estadisticaElement.getLiqBinRestoDocr1().add(estadisticaElement.getLiqBinRestoDocr2()).add(estadisticaElement.getLiqBinRestoDocr3()).add(estadisticaElement.getLiqBinRestoDocr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_TOTAL_DOCR1.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_TOTAL_DOCR1 := (:ESTADISTICA.LIQ_BIN_G1_DOCR1+:ESTADISTICA.LIQ_BIN_G2_DOCR1+:ESTADISTICA.LIQ_BIN_G3_DOCR1+:ESTADISTICA.LIQ_BIN_G4_DOCR1+:ESTADISTICA.LIQ_BIN_G5_DOCR1+:ESTADISTICA.LIQ_BIN_RESTO_DOCR1);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_TOTAL_DOCR1.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_TOTAL_DOCR1")
	public void liqBinTotalDocr1_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinTotalDocr1(((estadisticaElement.getLiqBinG1Docr1().add(estadisticaElement.getLiqBinG2Docr1()).add(estadisticaElement.getLiqBinG3Docr1()).add(estadisticaElement.getLiqBinG4Docr1()).add(estadisticaElement.getLiqBinG5Docr1()).add(estadisticaElement.getLiqBinRestoDocr1()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_TOTAL_DOCR2.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_TOTAL_DOCR2 := (:ESTADISTICA.LIQ_BIN_G1_DOCR2+:ESTADISTICA.LIQ_BIN_G2_DOCR2+:ESTADISTICA.LIQ_BIN_G3_DOCR2+:ESTADISTICA.LIQ_BIN_G4_DOCR2+:ESTADISTICA.LIQ_BIN_G5_DOCR2+:ESTADISTICA.LIQ_BIN_RESTO_DOCR2);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_TOTAL_DOCR2.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_TOTAL_DOCR2")
	public void liqBinTotalDocr2_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinTotalDocr2(((estadisticaElement.getLiqBinG1Docr2().add(estadisticaElement.getLiqBinG2Docr2()).add(estadisticaElement.getLiqBinG3Docr2()).add(estadisticaElement.getLiqBinG4Docr2()).add(estadisticaElement.getLiqBinG5Docr2()).add(estadisticaElement.getLiqBinRestoDocr2()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_TOTAL_DOCR3.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_TOTAL_DOCR3 := (:ESTADISTICA.LIQ_BIN_G1_DOCR3+:ESTADISTICA.LIQ_BIN_G2_DOCR3+:ESTADISTICA.LIQ_BIN_G3_DOCR3+:ESTADISTICA.LIQ_BIN_G4_DOCR3+:ESTADISTICA.LIQ_BIN_G5_DOCR3+:ESTADISTICA.LIQ_BIN_RESTO_DOCR3);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_TOTAL_DOCR3.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_TOTAL_DOCR3")
	public void liqBinTotalDocr3_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinTotalDocr3(((estadisticaElement.getLiqBinG1Docr3().add(estadisticaElement.getLiqBinG2Docr3()).add(estadisticaElement.getLiqBinG3Docr3()).add(estadisticaElement.getLiqBinG4Docr3()).add(estadisticaElement.getLiqBinG5Docr3()).add(estadisticaElement.getLiqBinRestoDocr3()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_TOTAL_DOCR4.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_TOTAL_DOCR4 := (:ESTADISTICA.LIQ_BIN_G1_DOCR4+:ESTADISTICA.LIQ_BIN_G2_DOCR4+:ESTADISTICA.LIQ_BIN_G3_DOCR4+:ESTADISTICA.LIQ_BIN_G4_DOCR4+:ESTADISTICA.LIQ_BIN_G5_DOCR4+:ESTADISTICA.LIQ_BIN_RESTO_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_TOTAL_DOCR4.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_TOTAL_DOCR4")
	public void liqBinTotalDocr4_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinTotalDocr4(((estadisticaElement.getLiqBinG1Docr4().add(estadisticaElement.getLiqBinG2Docr4()).add(estadisticaElement.getLiqBinG3Docr4()).add(estadisticaElement.getLiqBinG4Docr4()).add(estadisticaElement.getLiqBinG5Docr4()).add(estadisticaElement.getLiqBinRestoDocr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_TOTAL_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_TOTAL_DOCRTOTAL := (:ESTADISTICA.LIQ_BIN_TOTAL_DOCR1+:ESTADISTICA.LIQ_BIN_TOTAL_DOCR2+:ESTADISTICA.LIQ_BIN_TOTAL_DOCR3+:ESTADISTICA.LIQ_BIN_TOTAL_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_TOTAL_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_TOTAL_DOCRTOTAL")
	public void liqBinTotalDocrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinTotalDocrtotal(((estadisticaElement.getLiqBinTotalDocr1().add(estadisticaElement.getLiqBinTotalDocr2()).add(estadisticaElement.getLiqBinTotalDocr3()).add(estadisticaElement.getLiqBinTotalDocr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_G1_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_G1_RTOTAL := (:ESTADISTICA.LIQ_BIN_G1_R1+:ESTADISTICA.LIQ_BIN_G1_R2+:ESTADISTICA.LIQ_BIN_G1_R3+:ESTADISTICA.LIQ_BIN_G1_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_G1_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_G1_RTOTAL")
	public void liqBinG1Rtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinG1Rtotal(((estadisticaElement.getLiqBinG1R1().add(estadisticaElement.getLiqBinG1R2()).add(estadisticaElement.getLiqBinG1R3()).add(estadisticaElement.getLiqBinG1R4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_G2_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_G2_RTOTAL := (:ESTADISTICA.LIQ_BIN_G2_R1+:ESTADISTICA.LIQ_BIN_G2_R2+:ESTADISTICA.LIQ_BIN_G2_R3+:ESTADISTICA.LIQ_BIN_G2_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_G2_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_G2_RTOTAL")
	public void liqBinG2Rtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinG2Rtotal(((estadisticaElement.getLiqBinG2R1().add(estadisticaElement.getLiqBinG2R2()).add(estadisticaElement.getLiqBinG2R3()).add(estadisticaElement.getLiqBinG2R4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_G3_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_G3_RTOTAL := (:ESTADISTICA.LIQ_BIN_G3_R1+:ESTADISTICA.LIQ_BIN_G3_R2+:ESTADISTICA.LIQ_BIN_G3_R3+:ESTADISTICA.LIQ_BIN_G3_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_G3_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_G3_RTOTAL")
	public void liqBinG3Rtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinG3Rtotal(((estadisticaElement.getLiqBinG3R1().add(estadisticaElement.getLiqBinG3R2()).add(estadisticaElement.getLiqBinG3R3()).add(estadisticaElement.getLiqBinG3R4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_G4_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_G4_RTOTAL := (:ESTADISTICA.LIQ_BIN_G4_R1+:ESTADISTICA.LIQ_BIN_G4_R2+:ESTADISTICA.LIQ_BIN_G4_R3+:ESTADISTICA.LIQ_BIN_G4_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_G4_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_G4_RTOTAL")
	public void liqBinG4Rtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinG4Rtotal(((estadisticaElement.getLiqBinG4R1().add(estadisticaElement.getLiqBinG4R2()).add(estadisticaElement.getLiqBinG4R3()).add(estadisticaElement.getLiqBinG4R4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_G5_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_G5_RTOTAL := (:ESTADISTICA.LIQ_BIN_G5_R1+:ESTADISTICA.LIQ_BIN_G5_R2+:ESTADISTICA.LIQ_BIN_G5_R3+:ESTADISTICA.LIQ_BIN_G5_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_G5_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_G5_RTOTAL")
	public void liqBinG5Rtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinG5Rtotal(((estadisticaElement.getLiqBinG5R1().add(estadisticaElement.getLiqBinG5R2()).add(estadisticaElement.getLiqBinG5R3()).add(estadisticaElement.getLiqBinG5R4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_G6_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_G6_RTOTAL := (:ESTADISTICA.LIQ_BIN_G6_R1+:ESTADISTICA.LIQ_BIN_G6_R2+:ESTADISTICA.LIQ_BIN_G6_R3+:ESTADISTICA.LIQ_BIN_G6_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_G6_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_G6_RTOTAL")
	public void liqBinG6Rtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinG6Rtotal(((estadisticaElement.getLiqBinG6R1().add(estadisticaElement.getLiqBinG6R2()).add(estadisticaElement.getLiqBinG6R3()).add(estadisticaElement.getLiqBinG6R4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_RESTO_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_RESTO_RTOTAL := (:ESTADISTICA.LIQ_BIN_RESTO_R1+:ESTADISTICA.LIQ_BIN_RESTO_R2+:ESTADISTICA.LIQ_BIN_RESTO_R3+:ESTADISTICA.LIQ_BIN_RESTO_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_RESTO_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_RESTO_RTOTAL")
	public void liqBinRestoRtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinRestoRtotal(((estadisticaElement.getLiqBinRestoR1().add(estadisticaElement.getLiqBinRestoR2()).add(estadisticaElement.getLiqBinRestoR3()).add(estadisticaElement.getLiqBinRestoR4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_TOTAL_R1.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_TOTAL_R1 := (:ESTADISTICA.LIQ_BIN_G1_R1+:ESTADISTICA.LIQ_BIN_G2_R1+:ESTADISTICA.LIQ_BIN_G3_R1+:ESTADISTICA.LIQ_BIN_G4_R1+:ESTADISTICA.LIQ_BIN_G5_R1+:ESTADISTICA.LIQ_BIN_RESTO_R1);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_TOTAL_R1.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_TOTAL_R1")
	public void liqBinTotalR1_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinTotalR1(((estadisticaElement.getLiqBinG1R1().add(estadisticaElement.getLiqBinG2R1()).add(estadisticaElement.getLiqBinG3R1()).add(estadisticaElement.getLiqBinG4R1()).add(estadisticaElement.getLiqBinG5R1()).add(estadisticaElement.getLiqBinRestoR1()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_TOTAL_R2.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_TOTAL_R2 := (:ESTADISTICA.LIQ_BIN_G1_R2+:ESTADISTICA.LIQ_BIN_G2_R2+:ESTADISTICA.LIQ_BIN_G3_R2+:ESTADISTICA.LIQ_BIN_G4_R2+:ESTADISTICA.LIQ_BIN_G5_R2+:ESTADISTICA.LIQ_BIN_RESTO_R2);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_TOTAL_R2.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_TOTAL_R2")
	public void liqBinTotalR2_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinTotalR2(((estadisticaElement.getLiqBinG1R2().add(estadisticaElement.getLiqBinG2R2()).add(estadisticaElement.getLiqBinG3R2()).add(estadisticaElement.getLiqBinG4R2()).add(estadisticaElement.getLiqBinG5R2()).add(estadisticaElement.getLiqBinRestoR2()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_TOTAL_R3.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_TOTAL_R3 := (:ESTADISTICA.LIQ_BIN_G1_R3+:ESTADISTICA.LIQ_BIN_G2_R3+:ESTADISTICA.LIQ_BIN_G3_R3+:ESTADISTICA.LIQ_BIN_G4_R3+:ESTADISTICA.LIQ_BIN_G5_R3+:ESTADISTICA.LIQ_BIN_RESTO_R3);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_TOTAL_R3.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_TOTAL_R3")
	public void liqBinTotalR3_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinTotalR3(((estadisticaElement.getLiqBinG1R3().add(estadisticaElement.getLiqBinG2R3()).add(estadisticaElement.getLiqBinG3R3()).add(estadisticaElement.getLiqBinG4R3()).add(estadisticaElement.getLiqBinG5R3()).add(estadisticaElement.getLiqBinRestoR3()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_TOTAL_R4.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_TOTAL_R4 := (:ESTADISTICA.LIQ_BIN_G1_R4+:ESTADISTICA.LIQ_BIN_G2_R4+:ESTADISTICA.LIQ_BIN_G3_R4+:ESTADISTICA.LIQ_BIN_G4_R4+:ESTADISTICA.LIQ_BIN_G5_R4+:ESTADISTICA.LIQ_BIN_RESTO_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_TOTAL_R4.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_TOTAL_R4")
	public void liqBinTotalR4_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinTotalR4(((estadisticaElement.getLiqBinG1R4().add(estadisticaElement.getLiqBinG2R4()).add(estadisticaElement.getLiqBinG3R4()).add(estadisticaElement.getLiqBinG4R4()).add(estadisticaElement.getLiqBinG5R4()).add(estadisticaElement.getLiqBinRestoR4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_BIN_TOTAL_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_BIN_TOTAL_RTOTAL := (:ESTADISTICA.LIQ_BIN_TOTAL_R1+:ESTADISTICA.LIQ_BIN_TOTAL_R2+:ESTADISTICA.LIQ_BIN_TOTAL_R3+:ESTADISTICA.LIQ_BIN_TOTAL_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_BIN_TOTAL_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_BIN_TOTAL_RTOTAL")
	public void liqBinTotalRtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqBinTotalRtotal(((estadisticaElement.getLiqBinTotalR1().add(estadisticaElement.getLiqBinTotalR2()).add(estadisticaElement.getLiqBinTotalR3()).add(estadisticaElement.getLiqBinTotalR4()))));
	}

	/* Original PL/SQL code code for TRIGGER RB_LIQ_BIN_PERIODO.WHEN-RADIO-CHANGED
	 VISUALIZACION_LIQ_BINGO;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * RB_LIQ_BIN_PERIODO.WHEN-RADIO-CHANGED
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "WHEN-RADIO-CHANGED", item = "RB_LIQ_BIN_PERIODO")
	public void rbLiqBinPeriodo_radioGroupChange() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		this.getTask().getServices().visualizacionLiqBingo(estadisticaElement);
	}

	/* Original PL/SQL code code for TRIGGER CASINO_DOCTTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.CASINO_DOCTTOTAL := (:ESTADISTICA.CASINO_DOCT1+:ESTADISTICA.CASINO_DOCT2+:ESTADISTICA.CASINO_DOCT3+:ESTADISTICA.CASINO_DOCT4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * CASINO_DOCTTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "CASINO_DOCTTOTAL")
	public void casinoDocttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setCasinoDocttotal(((estadisticaElement.getCasinoDoct1().add(estadisticaElement.getCasinoDoct2()).add(estadisticaElement.getCasinoDoct3()).add(estadisticaElement.getCasinoDoct4()))));
	}

	/* Original PL/SQL code code for TRIGGER CASINO_TTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.CASINO_TTOTAL := (:ESTADISTICA.CASINO_T1+:ESTADISTICA.CASINO_T2+:ESTADISTICA.CASINO_T3+:ESTADISTICA.CASINO_T4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * CASINO_TTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "CASINO_TTOTAL")
	public void casinoTtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setCasinoTtotal(((estadisticaElement.getCasinoT1().add(estadisticaElement.getCasinoT2()).add(estadisticaElement.getCasinoT3()).add(estadisticaElement.getCasinoT4()))));
	}

	/* Original PL/SQL code code for TRIGGER CASINO_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.CASINO_DOCRTOTAL := (:ESTADISTICA.CASINO_DOCR1+:ESTADISTICA.CASINO_DOCR2+:ESTADISTICA.CASINO_DOCR3+:ESTADISTICA.CASINO_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * CASINO_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "CASINO_DOCRTOTAL")
	public void casinoDocrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setCasinoDocrtotal(((estadisticaElement.getCasinoDocr1().add(estadisticaElement.getCasinoDocr2()).add(estadisticaElement.getCasinoDocr3()).add(estadisticaElement.getCasinoDocr4()))));
	}

	/* Original PL/SQL code code for TRIGGER CASINO_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.CASINO_RTOTAL := (:ESTADISTICA.CASINO_R1+:ESTADISTICA.CASINO_R2+:ESTADISTICA.CASINO_R3+:ESTADISTICA.CASINO_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * CASINO_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "CASINO_RTOTAL")
	public void casinoRtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setCasinoRtotal(((estadisticaElement.getCasinoR1().add(estadisticaElement.getCasinoR2()).add(estadisticaElement.getCasinoR3()).add(estadisticaElement.getCasinoR4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G1_DOCTTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G1_DOCTTOTAL := (:ESTADISTICA.LIQ_CAS_G1_DOCT1+:ESTADISTICA.LIQ_CAS_G1_DOCT2+:ESTADISTICA.LIQ_CAS_G1_DOCT3+:ESTADISTICA.LIQ_CAS_G1_DOCT4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G1_DOCTTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G1_DOCTTOTAL")
	public void liqCasG1Docttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG1Docttotal(((estadisticaElement.getLiqCasG1Doct1().add(estadisticaElement.getLiqCasG1Doct2()).add(estadisticaElement.getLiqCasG1Doct3()).add(estadisticaElement.getLiqCasG1Doct4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G2_DOCTTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G2_DOCTTOTAL := (:ESTADISTICA.LIQ_CAS_G2_DOCT1+:ESTADISTICA.LIQ_CAS_G2_DOCT2+:ESTADISTICA.LIQ_CAS_G2_DOCT3+:ESTADISTICA.LIQ_CAS_G2_DOCT4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G2_DOCTTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G2_DOCTTOTAL")
	public void liqCasG2Docttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG2Docttotal(((estadisticaElement.getLiqCasG2Doct1().add(estadisticaElement.getLiqCasG2Doct2()).add(estadisticaElement.getLiqCasG2Doct3()).add(estadisticaElement.getLiqCasG2Doct4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G3_DOCTTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G3_DOCTTOTAL := (:ESTADISTICA.LIQ_CAS_G3_DOCT1+:ESTADISTICA.LIQ_CAS_G3_DOCT2+:ESTADISTICA.LIQ_CAS_G3_DOCT3+:ESTADISTICA.LIQ_CAS_G3_DOCT4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G3_DOCTTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G3_DOCTTOTAL")
	public void liqCasG3Docttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG3Docttotal(((estadisticaElement.getLiqCasG3Doct1().add(estadisticaElement.getLiqCasG3Doct2()).add(estadisticaElement.getLiqCasG3Doct3()).add(estadisticaElement.getLiqCasG3Doct4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G4_DOCTTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G4_DOCTTOTAL := (:ESTADISTICA.LIQ_CAS_G4_DOCT1+:ESTADISTICA.LIQ_CAS_G4_DOCT2+:ESTADISTICA.LIQ_CAS_G4_DOCT3+:ESTADISTICA.LIQ_CAS_G4_DOCT4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G4_DOCTTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G4_DOCTTOTAL")
	public void liqCasG4Docttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG4Docttotal(((estadisticaElement.getLiqCasG4Doct1().add(estadisticaElement.getLiqCasG4Doct2()).add(estadisticaElement.getLiqCasG4Doct3()).add(estadisticaElement.getLiqCasG4Doct4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G5_DOCTTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G5_DOCTTOTAL := (:ESTADISTICA.LIQ_CAS_G5_DOCT1+:ESTADISTICA.LIQ_CAS_G5_DOCT2+:ESTADISTICA.LIQ_CAS_G5_DOCT3+:ESTADISTICA.LIQ_CAS_G5_DOCT4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G5_DOCTTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G5_DOCTTOTAL")
	public void liqCasG5Docttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG5Docttotal(((estadisticaElement.getLiqCasG5Doct1().add(estadisticaElement.getLiqCasG5Doct2()).add(estadisticaElement.getLiqCasG5Doct3()).add(estadisticaElement.getLiqCasG5Doct4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G6_DOCTTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G6_DOCTTOTAL := (:ESTADISTICA.LIQ_CAS_G6_DOCT1+:ESTADISTICA.LIQ_CAS_G6_DOCT2+:ESTADISTICA.LIQ_CAS_G6_DOCT3+:ESTADISTICA.LIQ_CAS_G6_DOCT4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G6_DOCTTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G6_DOCTTOTAL")
	public void liqCasG6Docttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG6Docttotal(((estadisticaElement.getLiqCasG6Doct1().add(estadisticaElement.getLiqCasG6Doct2()).add(estadisticaElement.getLiqCasG6Doct3()).add(estadisticaElement.getLiqCasG6Doct4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_RESTO_DOCTTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_RESTO_DOCTTOTAL := (:ESTADISTICA.LIQ_CAS_RESTO_DOCT1+:ESTADISTICA.LIQ_CAS_RESTO_DOCT2+:ESTADISTICA.LIQ_CAS_RESTO_DOCT3+:ESTADISTICA.LIQ_CAS_RESTO_DOCT4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_RESTO_DOCTTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_RESTO_DOCTTOTAL")
	public void liqCasRestoDocttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasRestoDocttotal(((estadisticaElement.getLiqCasRestoDoct1().add(estadisticaElement.getLiqCasRestoDoct2()).add(estadisticaElement.getLiqCasRestoDoct3()).add(estadisticaElement.getLiqCasRestoDoct4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_TOTAL_DOCT1.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_TOTAL_DOCT1 := (:ESTADISTICA.LIQ_CAS_G1_DOCT1+:ESTADISTICA.LIQ_CAS_G2_DOCT1+:ESTADISTICA.LIQ_CAS_G3_DOCT1+:ESTADISTICA.LIQ_CAS_G4_DOCT1+:ESTADISTICA.LIQ_CAS_G5_DOCT1+:ESTADISTICA.LIQ_CAS_RESTO_DOCT1);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_TOTAL_DOCT1.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_TOTAL_DOCT1")
	public void liqCasTotalDoct1_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasTotalDoct1(((estadisticaElement.getLiqCasG1Doct1().add(estadisticaElement.getLiqCasG2Doct1()).add(estadisticaElement.getLiqCasG3Doct1()).add(estadisticaElement.getLiqCasG4Doct1()).add(estadisticaElement.getLiqCasG5Doct1()).add(estadisticaElement.getLiqCasRestoDoct1()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_TOTAL_DOCT2.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_TOTAL_DOCT2 := (:ESTADISTICA.LIQ_CAS_G1_DOCT2+:ESTADISTICA.LIQ_CAS_G2_DOCT2+:ESTADISTICA.LIQ_CAS_G3_DOCT2+:ESTADISTICA.LIQ_CAS_G4_DOCT2+:ESTADISTICA.LIQ_CAS_G5_DOCT2+:ESTADISTICA.LIQ_CAS_RESTO_DOCT2);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_TOTAL_DOCT2.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_TOTAL_DOCT2")
	public void liqCasTotalDoct2_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasTotalDoct2(((estadisticaElement.getLiqCasG1Doct2().add(estadisticaElement.getLiqCasG2Doct2()).add(estadisticaElement.getLiqCasG3Doct2()).add(estadisticaElement.getLiqCasG4Doct2()).add(estadisticaElement.getLiqCasG5Doct2()).add(estadisticaElement.getLiqCasRestoDoct2()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_TOTAL_DOCT3.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_TOTAL_DOCT3 := (:ESTADISTICA.LIQ_CAS_G1_DOCT3+:ESTADISTICA.LIQ_CAS_G2_DOCT3+:ESTADISTICA.LIQ_CAS_G3_DOCT3+:ESTADISTICA.LIQ_CAS_G4_DOCT3+:ESTADISTICA.LIQ_CAS_G5_DOCT3+:ESTADISTICA.LIQ_CAS_RESTO_DOCT3);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_TOTAL_DOCT3.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_TOTAL_DOCT3")
	public void liqCasTotalDoct3_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasTotalDoct3(((estadisticaElement.getLiqCasG1Doct3().add(estadisticaElement.getLiqCasG2Doct3()).add(estadisticaElement.getLiqCasG3Doct3()).add(estadisticaElement.getLiqCasG4Doct3()).add(estadisticaElement.getLiqCasG5Doct3()).add(estadisticaElement.getLiqCasRestoDoct3()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_TOTAL_DOCT4.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_TOTAL_DOCT4 := (:ESTADISTICA.LIQ_CAS_G1_DOCT4+:ESTADISTICA.LIQ_CAS_G2_DOCT4+:ESTADISTICA.LIQ_CAS_G3_DOCT4+:ESTADISTICA.LIQ_CAS_G4_DOCT4+:ESTADISTICA.LIQ_CAS_G5_DOCT4+:ESTADISTICA.LIQ_CAS_RESTO_DOCT4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_TOTAL_DOCT4.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_TOTAL_DOCT4")
	public void liqCasTotalDoct4_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasTotalDoct4(((estadisticaElement.getLiqCasG1Doct4().add(estadisticaElement.getLiqCasG2Doct4()).add(estadisticaElement.getLiqCasG3Doct4()).add(estadisticaElement.getLiqCasG4Doct4()).add(estadisticaElement.getLiqCasG5Doct4()).add(estadisticaElement.getLiqCasRestoDoct4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_TOTAL_DOCTTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_TOTAL_DOCTTOTAL := (:ESTADISTICA.LIQ_CAS_TOTAL_DOCT1+:ESTADISTICA.LIQ_CAS_TOTAL_DOCT2+:ESTADISTICA.LIQ_CAS_TOTAL_DOCT3+:ESTADISTICA.LIQ_CAS_TOTAL_DOCT4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_TOTAL_DOCTTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_TOTAL_DOCTTOTAL")
	public void liqCasTotalDocttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasTotalDocttotal(((estadisticaElement.getLiqCasTotalDoct1().add(estadisticaElement.getLiqCasTotalDoct2()).add(estadisticaElement.getLiqCasTotalDoct3()).add(estadisticaElement.getLiqCasTotalDoct4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G1_TTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G1_TTOTAL := (:ESTADISTICA.LIQ_CAS_G1_T1+:ESTADISTICA.LIQ_CAS_G1_T2+:ESTADISTICA.LIQ_CAS_G1_T3+:ESTADISTICA.LIQ_CAS_G1_T4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G1_TTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G1_TTOTAL")
	public void liqCasG1Ttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG1Ttotal(((estadisticaElement.getLiqCasG1T1().add(estadisticaElement.getLiqCasG1T2()).add(estadisticaElement.getLiqCasG1T3()).add(estadisticaElement.getLiqCasG1T4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G2_TTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G2_TTOTAL := (:ESTADISTICA.LIQ_CAS_G2_T1+:ESTADISTICA.LIQ_CAS_G2_T2+:ESTADISTICA.LIQ_CAS_G2_T3+:ESTADISTICA.LIQ_CAS_G2_T4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G2_TTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G2_TTOTAL")
	public void liqCasG2Ttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG2Ttotal(((estadisticaElement.getLiqCasG2T1().add(estadisticaElement.getLiqCasG2T2()).add(estadisticaElement.getLiqCasG2T3()).add(estadisticaElement.getLiqCasG2T4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G3_TTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G3_TTOTAL := (:ESTADISTICA.LIQ_CAS_G3_T1+:ESTADISTICA.LIQ_CAS_G3_T2+:ESTADISTICA.LIQ_CAS_G3_T3+:ESTADISTICA.LIQ_CAS_G3_T4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G3_TTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G3_TTOTAL")
	public void liqCasG3Ttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG3Ttotal(((estadisticaElement.getLiqCasG3T1().add(estadisticaElement.getLiqCasG3T2()).add(estadisticaElement.getLiqCasG3T3()).add(estadisticaElement.getLiqCasG3T4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G4_TTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G4_TTOTAL := (:ESTADISTICA.LIQ_CAS_G4_T1+:ESTADISTICA.LIQ_CAS_G4_T2+:ESTADISTICA.LIQ_CAS_G4_T3+:ESTADISTICA.LIQ_CAS_G4_T4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G4_TTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G4_TTOTAL")
	public void liqCasG4Ttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG4Ttotal(((estadisticaElement.getLiqCasG4T1().add(estadisticaElement.getLiqCasG4T2()).add(estadisticaElement.getLiqCasG4T3()).add(estadisticaElement.getLiqCasG4T4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G5_TTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G5_TTOTAL := (:ESTADISTICA.LIQ_CAS_G5_T1+:ESTADISTICA.LIQ_CAS_G5_T2+:ESTADISTICA.LIQ_CAS_G5_T3+:ESTADISTICA.LIQ_CAS_G5_T4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G5_TTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G5_TTOTAL")
	public void liqCasG5Ttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG5Ttotal(((estadisticaElement.getLiqCasG5T1().add(estadisticaElement.getLiqCasG5T2()).add(estadisticaElement.getLiqCasG5T3()).add(estadisticaElement.getLiqCasG5T4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G6_TTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G6_TTOTAL := (:ESTADISTICA.LIQ_CAS_G6_T1+:ESTADISTICA.LIQ_CAS_G6_T2+:ESTADISTICA.LIQ_CAS_G6_T3+:ESTADISTICA.LIQ_CAS_G6_T4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G6_TTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G6_TTOTAL")
	public void liqCasG6Ttotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG6Ttotal(((estadisticaElement.getLiqCasG6T1().add(estadisticaElement.getLiqCasG6T2()).add(estadisticaElement.getLiqCasG6T3()).add(estadisticaElement.getLiqCasG6T4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_RESTO_TTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_RESTO_TTOTAL := (:ESTADISTICA.LIQ_CAS_RESTO_T1+:ESTADISTICA.LIQ_CAS_RESTO_T2+:ESTADISTICA.LIQ_CAS_RESTO_T3+:ESTADISTICA.LIQ_CAS_RESTO_T4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_RESTO_TTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_RESTO_TTOTAL")
	public void liqCasRestoTtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasRestoTtotal(((estadisticaElement.getLiqCasRestoT1().add(estadisticaElement.getLiqCasRestoT2()).add(estadisticaElement.getLiqCasRestoT3()).add(estadisticaElement.getLiqCasRestoT4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_TOTAL_T1.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_TOTAL_T1 := (:ESTADISTICA.LIQ_CAS_G1_T1+:ESTADISTICA.LIQ_CAS_G2_T1+:ESTADISTICA.LIQ_CAS_G3_T1+:ESTADISTICA.LIQ_CAS_G4_T1+:ESTADISTICA.LIQ_CAS_G5_T1+:ESTADISTICA.LIQ_CAS_RESTO_T1);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_TOTAL_T1.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_TOTAL_T1")
	public void liqCasTotalT1_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasTotalT1(((estadisticaElement.getLiqCasG1T1().add(estadisticaElement.getLiqCasG2T1()).add(estadisticaElement.getLiqCasG3T1()).add(estadisticaElement.getLiqCasG4T1()).add(estadisticaElement.getLiqCasG5T1()).add(estadisticaElement.getLiqCasRestoT1()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_TOTAL_T2.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_TOTAL_T2 := (:ESTADISTICA.LIQ_CAS_G1_T2+:ESTADISTICA.LIQ_CAS_G2_T2+:ESTADISTICA.LIQ_CAS_G3_T2+:ESTADISTICA.LIQ_CAS_G4_T2+:ESTADISTICA.LIQ_CAS_G5_T2+:ESTADISTICA.LIQ_CAS_RESTO_T2);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_TOTAL_T2.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_TOTAL_T2")
	public void liqCasTotalT2_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasTotalT2(((estadisticaElement.getLiqCasG1T2().add(estadisticaElement.getLiqCasG2T2()).add(estadisticaElement.getLiqCasG3T2()).add(estadisticaElement.getLiqCasG4T2()).add(estadisticaElement.getLiqCasG5T2()).add(estadisticaElement.getLiqCasRestoT2()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_TOTAL_T3.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_TOTAL_T3 := (:ESTADISTICA.LIQ_CAS_G1_T3+:ESTADISTICA.LIQ_CAS_G2_T3+:ESTADISTICA.LIQ_CAS_G3_T3+:ESTADISTICA.LIQ_CAS_G4_T3+:ESTADISTICA.LIQ_CAS_G5_T3+:ESTADISTICA.LIQ_CAS_RESTO_T3);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_TOTAL_T3.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_TOTAL_T3")
	public void liqCasTotalT3_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasTotalT3(((estadisticaElement.getLiqCasG1T3().add(estadisticaElement.getLiqCasG2T3()).add(estadisticaElement.getLiqCasG3T3()).add(estadisticaElement.getLiqCasG4T3()).add(estadisticaElement.getLiqCasG5T3()).add(estadisticaElement.getLiqCasRestoT3()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_TOTAL_T4.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_TOTAL_T4 := (:ESTADISTICA.LIQ_CAS_G1_T4+:ESTADISTICA.LIQ_CAS_G2_T4+:ESTADISTICA.LIQ_CAS_G3_T4+:ESTADISTICA.LIQ_CAS_G4_T4+:ESTADISTICA.LIQ_CAS_G5_T4+:ESTADISTICA.LIQ_CAS_RESTO_T4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_TOTAL_T4.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_TOTAL_T4")
	public void liqCasTotalT4_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasTotalT4(((estadisticaElement.getLiqCasG1T4().add(estadisticaElement.getLiqCasG2T4()).add(estadisticaElement.getLiqCasG3T4()).add(estadisticaElement.getLiqCasG4T4()).add(estadisticaElement.getLiqCasG5T4()).add(estadisticaElement.getLiqCasRestoT4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_TOTAL_TTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_TOTAL_TTOTAL := (:ESTADISTICA.LIQ_CAS_TOTAL_T1+:ESTADISTICA.LIQ_CAS_TOTAL_T2+:ESTADISTICA.LIQ_CAS_TOTAL_T3+:ESTADISTICA.LIQ_CAS_TOTAL_T4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_TOTAL_TTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_TOTAL_TTOTAL")
	public void liqCasTotalTtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasTotalTtotal(((estadisticaElement.getLiqCasTotalT1().add(estadisticaElement.getLiqCasTotalT2()).add(estadisticaElement.getLiqCasTotalT3()).add(estadisticaElement.getLiqCasTotalT4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G1_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G1_DOCRTOTAL := (:ESTADISTICA.LIQ_CAS_G1_DOCR1+:ESTADISTICA.LIQ_CAS_G1_DOCR2+:ESTADISTICA.LIQ_CAS_G1_DOCR3+:ESTADISTICA.LIQ_CAS_G1_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G1_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G1_DOCRTOTAL")
	public void liqCasG1Docrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG1Docrtotal(((estadisticaElement.getLiqCasG1Docr1().add(estadisticaElement.getLiqCasG1Docr2()).add(estadisticaElement.getLiqCasG1Docr3()).add(estadisticaElement.getLiqCasG1Docr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G2_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G2_DOCRTOTAL := (:ESTADISTICA.LIQ_CAS_G2_DOCR1+:ESTADISTICA.LIQ_CAS_G2_DOCR2+:ESTADISTICA.LIQ_CAS_G2_DOCR3+:ESTADISTICA.LIQ_CAS_G2_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G2_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G2_DOCRTOTAL")
	public void liqCasG2Docrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG2Docrtotal(((estadisticaElement.getLiqCasG2Docr1().add(estadisticaElement.getLiqCasG2Docr2()).add(estadisticaElement.getLiqCasG2Docr3()).add(estadisticaElement.getLiqCasG2Docr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G3_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G3_DOCRTOTAL := (:ESTADISTICA.LIQ_CAS_G3_DOCR1+:ESTADISTICA.LIQ_CAS_G3_DOCR2+:ESTADISTICA.LIQ_CAS_G3_DOCR3+:ESTADISTICA.LIQ_CAS_G3_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G3_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G3_DOCRTOTAL")
	public void liqCasG3Docrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG3Docrtotal(((estadisticaElement.getLiqCasG3Docr1().add(estadisticaElement.getLiqCasG3Docr2()).add(estadisticaElement.getLiqCasG3Docr3()).add(estadisticaElement.getLiqCasG3Docr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G4_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G4_DOCRTOTAL := (:ESTADISTICA.LIQ_CAS_G4_DOCR1+:ESTADISTICA.LIQ_CAS_G4_DOCR2+:ESTADISTICA.LIQ_CAS_G4_DOCR3+:ESTADISTICA.LIQ_CAS_G4_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G4_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G4_DOCRTOTAL")
	public void liqCasG4Docrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG4Docrtotal(((estadisticaElement.getLiqCasG4Docr1().add(estadisticaElement.getLiqCasG4Docr2()).add(estadisticaElement.getLiqCasG4Docr3()).add(estadisticaElement.getLiqCasG4Docr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G5_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G5_DOCRTOTAL := (:ESTADISTICA.LIQ_CAS_G5_DOCR1+:ESTADISTICA.LIQ_CAS_G5_DOCR2+:ESTADISTICA.LIQ_CAS_G5_DOCR3+:ESTADISTICA.LIQ_CAS_G5_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G5_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G5_DOCRTOTAL")
	public void liqCasG5Docrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG5Docrtotal(((estadisticaElement.getLiqCasG5Docr1().add(estadisticaElement.getLiqCasG5Docr2()).add(estadisticaElement.getLiqCasG5Docr3()).add(estadisticaElement.getLiqCasG5Docr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G6_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G6_DOCRTOTAL := (:ESTADISTICA.LIQ_CAS_G6_DOCR1+:ESTADISTICA.LIQ_CAS_G6_DOCR2+:ESTADISTICA.LIQ_CAS_G6_DOCR3+:ESTADISTICA.LIQ_CAS_G6_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G6_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G6_DOCRTOTAL")
	public void liqCasG6Docrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG6Docrtotal(((estadisticaElement.getLiqCasG6Docr1().add(estadisticaElement.getLiqCasG6Docr2()).add(estadisticaElement.getLiqCasG6Docr3()).add(estadisticaElement.getLiqCasG6Docr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_RESTO_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_RESTO_DOCRTOTAL := (:ESTADISTICA.LIQ_CAS_RESTO_DOCR1+:ESTADISTICA.LIQ_CAS_RESTO_DOCR2+:ESTADISTICA.LIQ_CAS_RESTO_DOCR3+:ESTADISTICA.LIQ_CAS_RESTO_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_RESTO_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_RESTO_DOCRTOTAL")
	public void liqCasRestoDocrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasRestoDocrtotal(((estadisticaElement.getLiqCasRestoDocr1().add(estadisticaElement.getLiqCasRestoDocr2()).add(estadisticaElement.getLiqCasRestoDocr3()).add(estadisticaElement.getLiqCasRestoDocr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_TOTAL_DOCR1.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_TOTAL_DOCR1 := (:ESTADISTICA.LIQ_CAS_G1_DOCR1+:ESTADISTICA.LIQ_CAS_G2_DOCR1+:ESTADISTICA.LIQ_CAS_G3_DOCR1+:ESTADISTICA.LIQ_CAS_G4_DOCR1+:ESTADISTICA.LIQ_CAS_G5_DOCR1+:ESTADISTICA.LIQ_CAS_RESTO_DOCR1);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_TOTAL_DOCR1.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_TOTAL_DOCR1")
	public void liqCasTotalDocr1_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasTotalDocr1(((estadisticaElement.getLiqCasG1Docr1().add(estadisticaElement.getLiqCasG2Docr1()).add(estadisticaElement.getLiqCasG3Docr1()).add(estadisticaElement.getLiqCasG4Docr1()).add(estadisticaElement.getLiqCasG5Docr1()).add(estadisticaElement.getLiqCasRestoDocr1()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_TOTAL_DOCR2.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_TOTAL_DOCR2 := (:ESTADISTICA.LIQ_CAS_G1_DOCR2+:ESTADISTICA.LIQ_CAS_G2_DOCR2+:ESTADISTICA.LIQ_CAS_G3_DOCR2+:ESTADISTICA.LIQ_CAS_G4_DOCR2+:ESTADISTICA.LIQ_CAS_G5_DOCR2+:ESTADISTICA.LIQ_CAS_RESTO_DOCR2);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_TOTAL_DOCR2.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_TOTAL_DOCR2")
	public void liqCasTotalDocr2_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasTotalDocr2(((estadisticaElement.getLiqCasG1Docr2().add(estadisticaElement.getLiqCasG2Docr2()).add(estadisticaElement.getLiqCasG3Docr2()).add(estadisticaElement.getLiqCasG4Docr2()).add(estadisticaElement.getLiqCasG5Docr2()).add(estadisticaElement.getLiqCasRestoDocr2()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_TOTAL_DOCR3.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_TOTAL_DOCR3 := (:ESTADISTICA.LIQ_CAS_G1_DOCR3+:ESTADISTICA.LIQ_CAS_G2_DOCR3+:ESTADISTICA.LIQ_CAS_G3_DOCR3+:ESTADISTICA.LIQ_CAS_G4_DOCR3+:ESTADISTICA.LIQ_CAS_G5_DOCR3+:ESTADISTICA.LIQ_CAS_RESTO_DOCR3);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_TOTAL_DOCR3.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_TOTAL_DOCR3")
	public void liqCasTotalDocr3_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasTotalDocr3(((estadisticaElement.getLiqCasG1Docr3().add(estadisticaElement.getLiqCasG2Docr3()).add(estadisticaElement.getLiqCasG3Docr3()).add(estadisticaElement.getLiqCasG4Docr3()).add(estadisticaElement.getLiqCasG5Docr3()).add(estadisticaElement.getLiqCasRestoDocr3()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_TOTAL_DOCR4.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_TOTAL_DOCR4 := (:ESTADISTICA.LIQ_CAS_G1_DOCR4+:ESTADISTICA.LIQ_CAS_G2_DOCR4+:ESTADISTICA.LIQ_CAS_G3_DOCR4+:ESTADISTICA.LIQ_CAS_G4_DOCR4+:ESTADISTICA.LIQ_CAS_G5_DOCR4+:ESTADISTICA.LIQ_CAS_RESTO_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_TOTAL_DOCR4.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_TOTAL_DOCR4")
	public void liqCasTotalDocr4_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasTotalDocr4(((estadisticaElement.getLiqCasG1Docr4().add(estadisticaElement.getLiqCasG2Docr4()).add(estadisticaElement.getLiqCasG3Docr4()).add(estadisticaElement.getLiqCasG4Docr4()).add(estadisticaElement.getLiqCasG5Docr4()).add(estadisticaElement.getLiqCasRestoDocr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_TOTAL_DOCRTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_TOTAL_DOCRTOTAL := (:ESTADISTICA.LIQ_CAS_TOTAL_DOCR1+:ESTADISTICA.LIQ_CAS_TOTAL_DOCR2+:ESTADISTICA.LIQ_CAS_TOTAL_DOCR3+:ESTADISTICA.LIQ_CAS_TOTAL_DOCR4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_TOTAL_DOCRTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_TOTAL_DOCRTOTAL")
	public void liqCasTotalDocrtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasTotalDocrtotal(((estadisticaElement.getLiqCasTotalDocr1().add(estadisticaElement.getLiqCasTotalDocr2()).add(estadisticaElement.getLiqCasTotalDocr3()).add(estadisticaElement.getLiqCasTotalDocr4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G1_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G1_RTOTAL := (:ESTADISTICA.LIQ_CAS_G1_R1+:ESTADISTICA.LIQ_CAS_G1_R2+:ESTADISTICA.LIQ_CAS_G1_R3+:ESTADISTICA.LIQ_CAS_G1_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G1_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G1_RTOTAL")
	public void liqCasG1Rtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG1Rtotal(((estadisticaElement.getLiqCasG1R1().add(estadisticaElement.getLiqCasG1R2()).add(estadisticaElement.getLiqCasG1R3()).add(estadisticaElement.getLiqCasG1R4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G2_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G2_RTOTAL := (:ESTADISTICA.LIQ_CAS_G2_R1+:ESTADISTICA.LIQ_CAS_G2_R2+:ESTADISTICA.LIQ_CAS_G2_R3+:ESTADISTICA.LIQ_CAS_G2_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G2_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G2_RTOTAL")
	public void liqCasG2Rtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG2Rtotal(((estadisticaElement.getLiqCasG2R1().add(estadisticaElement.getLiqCasG2R2()).add(estadisticaElement.getLiqCasG2R3()).add(estadisticaElement.getLiqCasG2R4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G3_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G3_RTOTAL := (:ESTADISTICA.LIQ_CAS_G3_R1+:ESTADISTICA.LIQ_CAS_G3_R2+:ESTADISTICA.LIQ_CAS_G3_R3+:ESTADISTICA.LIQ_CAS_G3_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G3_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G3_RTOTAL")
	public void liqCasG3Rtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG3Rtotal(((estadisticaElement.getLiqCasG3R1().add(estadisticaElement.getLiqCasG3R2()).add(estadisticaElement.getLiqCasG3R3()).add(estadisticaElement.getLiqCasG3R4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G4_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G4_RTOTAL := (:ESTADISTICA.LIQ_CAS_G4_R1+:ESTADISTICA.LIQ_CAS_G4_R2+:ESTADISTICA.LIQ_CAS_G4_R3+:ESTADISTICA.LIQ_CAS_G4_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G4_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G4_RTOTAL")
	public void liqCasG4Rtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG4Rtotal(((estadisticaElement.getLiqCasG4R1().add(estadisticaElement.getLiqCasG4R2()).add(estadisticaElement.getLiqCasG4R3()).add(estadisticaElement.getLiqCasG4R4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G5_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G5_RTOTAL := (:ESTADISTICA.LIQ_CAS_G5_R1+:ESTADISTICA.LIQ_CAS_G5_R2+:ESTADISTICA.LIQ_CAS_G5_R3+:ESTADISTICA.LIQ_CAS_G5_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G5_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G5_RTOTAL")
	public void liqCasG5Rtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG5Rtotal(((estadisticaElement.getLiqCasG5R1().add(estadisticaElement.getLiqCasG5R2()).add(estadisticaElement.getLiqCasG5R3()).add(estadisticaElement.getLiqCasG5R4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_G6_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_G6_RTOTAL := (:ESTADISTICA.LIQ_CAS_G6_R1+:ESTADISTICA.LIQ_CAS_G6_R2+:ESTADISTICA.LIQ_CAS_G6_R3+:ESTADISTICA.LIQ_CAS_G6_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_G6_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_G6_RTOTAL")
	public void liqCasG6Rtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasG6Rtotal(((estadisticaElement.getLiqCasG6R1().add(estadisticaElement.getLiqCasG6R2()).add(estadisticaElement.getLiqCasG6R3()).add(estadisticaElement.getLiqCasG6R4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_RESTO_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_RESTO_RTOTAL := (:ESTADISTICA.LIQ_CAS_RESTO_R1+:ESTADISTICA.LIQ_CAS_RESTO_R2+:ESTADISTICA.LIQ_CAS_RESTO_R3+:ESTADISTICA.LIQ_CAS_RESTO_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_RESTO_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_RESTO_RTOTAL")
	public void liqCasRestoRtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasRestoRtotal(((estadisticaElement.getLiqCasRestoR1().add(estadisticaElement.getLiqCasRestoR2()).add(estadisticaElement.getLiqCasRestoR3()).add(estadisticaElement.getLiqCasRestoR4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_TOTAL_R1.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_TOTAL_R1 := (:ESTADISTICA.LIQ_CAS_G1_R1+:ESTADISTICA.LIQ_CAS_G2_R1+:ESTADISTICA.LIQ_CAS_G3_R1+:ESTADISTICA.LIQ_CAS_G4_R1+:ESTADISTICA.LIQ_CAS_G5_R1+:ESTADISTICA.LIQ_CAS_RESTO_R1);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_TOTAL_R1.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_TOTAL_R1")
	public void liqCasTotalR1_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasTotalR1(((estadisticaElement.getLiqCasG1R1().add(estadisticaElement.getLiqCasG2R1()).add(estadisticaElement.getLiqCasG3R1()).add(estadisticaElement.getLiqCasG4R1()).add(estadisticaElement.getLiqCasG5R1()).add(estadisticaElement.getLiqCasRestoR1()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_TOTAL_R2.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_TOTAL_R2 := (:ESTADISTICA.LIQ_CAS_G1_R2+:ESTADISTICA.LIQ_CAS_G2_R2+:ESTADISTICA.LIQ_CAS_G3_R2+:ESTADISTICA.LIQ_CAS_G4_R2+:ESTADISTICA.LIQ_CAS_G5_R2+:ESTADISTICA.LIQ_CAS_RESTO_R2);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_TOTAL_R2.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_TOTAL_R2")
	public void liqCasTotalR2_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasTotalR2(((estadisticaElement.getLiqCasG1R2().add(estadisticaElement.getLiqCasG2R2()).add(estadisticaElement.getLiqCasG3R2()).add(estadisticaElement.getLiqCasG4R2()).add(estadisticaElement.getLiqCasG5R2()).add(estadisticaElement.getLiqCasRestoR2()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_TOTAL_R3.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_TOTAL_R3 := (:ESTADISTICA.LIQ_CAS_G1_R3+:ESTADISTICA.LIQ_CAS_G2_R3+:ESTADISTICA.LIQ_CAS_G3_R3+:ESTADISTICA.LIQ_CAS_G4_R3+:ESTADISTICA.LIQ_CAS_G5_R3+:ESTADISTICA.LIQ_CAS_RESTO_R3);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_TOTAL_R3.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_TOTAL_R3")
	public void liqCasTotalR3_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasTotalR3(((estadisticaElement.getLiqCasG1R3().add(estadisticaElement.getLiqCasG2R3()).add(estadisticaElement.getLiqCasG3R3()).add(estadisticaElement.getLiqCasG4R3()).add(estadisticaElement.getLiqCasG5R3()).add(estadisticaElement.getLiqCasRestoR3()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_TOTAL_R4.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_TOTAL_R4 := (:ESTADISTICA.LIQ_CAS_G1_R4+:ESTADISTICA.LIQ_CAS_G2_R4+:ESTADISTICA.LIQ_CAS_G3_R4+:ESTADISTICA.LIQ_CAS_G4_R4+:ESTADISTICA.LIQ_CAS_G5_R4+:ESTADISTICA.LIQ_CAS_RESTO_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_TOTAL_R4.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_TOTAL_R4")
	public void liqCasTotalR4_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasTotalR4(((estadisticaElement.getLiqCasG1R4().add(estadisticaElement.getLiqCasG2R4()).add(estadisticaElement.getLiqCasG3R4()).add(estadisticaElement.getLiqCasG4R4()).add(estadisticaElement.getLiqCasG5R4()).add(estadisticaElement.getLiqCasRestoR4()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_CAS_TOTAL_RTOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_CAS_TOTAL_RTOTAL := (:ESTADISTICA.LIQ_CAS_TOTAL_R1+:ESTADISTICA.LIQ_CAS_TOTAL_R2+:ESTADISTICA.LIQ_CAS_TOTAL_R3+:ESTADISTICA.LIQ_CAS_TOTAL_R4);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_CAS_TOTAL_RTOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_CAS_TOTAL_RTOTAL")
	public void liqCasTotalRtotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqCasTotalRtotal(((estadisticaElement.getLiqCasTotalR1().add(estadisticaElement.getLiqCasTotalR2()).add(estadisticaElement.getLiqCasTotalR3()).add(estadisticaElement.getLiqCasTotalR4()))));
	}

	/* Original PL/SQL code code for TRIGGER RB_LIQ_CAS_PERIODO.WHEN-RADIO-CHANGED
	 VISUALIZACION_LIQ_CASINO;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * RB_LIQ_CAS_PERIODO.WHEN-RADIO-CHANGED
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "WHEN-RADIO-CHANGED", item = "RB_LIQ_CAS_PERIODO")
	public void rbLiqCasPeriodo_radioGroupChange() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		this.getTask().getServices().visualizacionLiqCasino(estadisticaElement);
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_DOC_G1_TOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_DOC_G1_TOTAL := (:ESTADISTICA.LIQ_OTRO_DOC_G1_COMB+:ESTADISTICA.LIQ_OTRO_DOC_G1_APUE+:ESTADISTICA.LIQ_OTRO_DOC_G1_RIFA+:ESTADISTICA.LIQ_OTRO_DOC_G1_BOLE);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_DOC_G1_TOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_DOC_G1_TOTAL")
	public void liqOtroDocG1Total_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroDocG1Total(((estadisticaElement.getLiqOtroDocG1Comb().add(estadisticaElement.getLiqOtroDocG1Apue()).add(estadisticaElement.getLiqOtroDocG1Rifa()).add(estadisticaElement.getLiqOtroDocG1Bole()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_DOC_G2_TOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_DOC_G2_TOTAL := (:ESTADISTICA.LIQ_OTRO_DOC_G2_COMB+:ESTADISTICA.LIQ_OTRO_DOC_G2_APUE+:ESTADISTICA.LIQ_OTRO_DOC_G2_RIFA+:ESTADISTICA.LIQ_OTRO_DOC_G2_BOLE);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_DOC_G2_TOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_DOC_G2_TOTAL")
	public void liqOtroDocG2Total_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroDocG2Total(((estadisticaElement.getLiqOtroDocG2Comb().add(estadisticaElement.getLiqOtroDocG2Apue()).add(estadisticaElement.getLiqOtroDocG2Rifa()).add(estadisticaElement.getLiqOtroDocG2Bole()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_DOC_G3_TOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_DOC_G3_TOTAL := (:ESTADISTICA.LIQ_OTRO_DOC_G3_COMB+:ESTADISTICA.LIQ_OTRO_DOC_G3_APUE+:ESTADISTICA.LIQ_OTRO_DOC_G3_RIFA+:ESTADISTICA.LIQ_OTRO_DOC_G3_BOLE);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_DOC_G3_TOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_DOC_G3_TOTAL")
	public void liqOtroDocG3Total_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroDocG3Total(((estadisticaElement.getLiqOtroDocG3Comb().add(estadisticaElement.getLiqOtroDocG3Apue()).add(estadisticaElement.getLiqOtroDocG3Rifa()).add(estadisticaElement.getLiqOtroDocG3Bole()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_DOC_G4_TOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_DOC_G4_TOTAL := (:ESTADISTICA.LIQ_OTRO_DOC_G4_COMB+:ESTADISTICA.LIQ_OTRO_DOC_G4_APUE+:ESTADISTICA.LIQ_OTRO_DOC_G4_RIFA+:ESTADISTICA.LIQ_OTRO_DOC_G4_BOLE);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_DOC_G4_TOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_DOC_G4_TOTAL")
	public void liqOtroDocG4Total_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroDocG4Total(((estadisticaElement.getLiqOtroDocG4Comb().add(estadisticaElement.getLiqOtroDocG4Apue()).add(estadisticaElement.getLiqOtroDocG4Rifa()).add(estadisticaElement.getLiqOtroDocG4Bole()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_DOC_G5_TOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_DOC_G5_TOTAL := (:ESTADISTICA.LIQ_OTRO_DOC_G5_COMB+:ESTADISTICA.LIQ_OTRO_DOC_G5_APUE+:ESTADISTICA.LIQ_OTRO_DOC_G5_RIFA+:ESTADISTICA.LIQ_OTRO_DOC_G5_BOLE);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_DOC_G5_TOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_DOC_G5_TOTAL")
	public void liqOtroDocG5Total_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroDocG5Total(((estadisticaElement.getLiqOtroDocG5Comb().add(estadisticaElement.getLiqOtroDocG5Apue()).add(estadisticaElement.getLiqOtroDocG5Rifa()).add(estadisticaElement.getLiqOtroDocG5Bole()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_DOC_G6_TOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_DOC_G6_TOTAL := (:ESTADISTICA.LIQ_OTRO_DOC_G6_COMB+:ESTADISTICA.LIQ_OTRO_DOC_G6_APUE+:ESTADISTICA.LIQ_OTRO_DOC_G6_RIFA+:ESTADISTICA.LIQ_OTRO_DOC_G6_BOLE);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_DOC_G6_TOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_DOC_G6_TOTAL")
	public void liqOtroDocG6Total_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroDocG6Total(((estadisticaElement.getLiqOtroDocG6Comb().add(estadisticaElement.getLiqOtroDocG6Apue()).add(estadisticaElement.getLiqOtroDocG6Rifa()).add(estadisticaElement.getLiqOtroDocG6Bole()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_DOC_RESTO_TOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_DOC_RESTO_TOTAL := (:ESTADISTICA.LIQ_OTRO_DOC_RESTO_COMB+:ESTADISTICA.LIQ_OTRO_DOC_RESTO_APUE+:ESTADISTICA.LIQ_OTRO_DOC_RESTO_RIFA+:ESTADISTICA.LIQ_OTRO_DOC_RESTO_BOLE);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_DOC_RESTO_TOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_DOC_RESTO_TOTAL")
	public void liqOtroDocRestoTotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroDocRestoTotal(((estadisticaElement.getLiqOtroDocRestoComb().add(estadisticaElement.getLiqOtroDocRestoApue()).add(estadisticaElement.getLiqOtroDocRestoRifa()).add(estadisticaElement.getLiqOtroDocRestoBole()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_DOC_TOTAL_COMB.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_DOC_TOTAL_COMB := (:ESTADISTICA.LIQ_OTRO_DOC_G1_COMB+:ESTADISTICA.LIQ_OTRO_DOC_G2_COMB+:ESTADISTICA.LIQ_OTRO_DOC_G3_COMB+:ESTADISTICA.LIQ_OTRO_DOC_G4_COMB+:ESTADISTICA.LIQ_OTRO_DOC_G5_COMB+:ESTADISTICA.LIQ_OTRO_DOC_RESTO_COMB);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_DOC_TOTAL_COMB.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_DOC_TOTAL_COMB")
	public void liqOtroDocTotalComb_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroDocTotalComb(((estadisticaElement.getLiqOtroDocG1Comb().add(estadisticaElement.getLiqOtroDocG2Comb()).add(estadisticaElement.getLiqOtroDocG3Comb()).add(estadisticaElement.getLiqOtroDocG4Comb()).add(estadisticaElement.getLiqOtroDocG5Comb()).add(estadisticaElement.getLiqOtroDocRestoComb()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_DOC_TOTAL_APUE.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_DOC_TOTAL_APUE := (:ESTADISTICA.LIQ_OTRO_DOC_G1_APUE+:ESTADISTICA.LIQ_OTRO_DOC_G2_APUE+:ESTADISTICA.LIQ_OTRO_DOC_G3_APUE+:ESTADISTICA.LIQ_OTRO_DOC_G4_APUE+:ESTADISTICA.LIQ_OTRO_DOC_G5_APUE+:ESTADISTICA.LIQ_OTRO_DOC_RESTO_APUE);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_DOC_TOTAL_APUE.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_DOC_TOTAL_APUE")
	public void liqOtroDocTotalApue_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroDocTotalApue(((estadisticaElement.getLiqOtroDocG1Apue().add(estadisticaElement.getLiqOtroDocG2Apue()).add(estadisticaElement.getLiqOtroDocG3Apue()).add(estadisticaElement.getLiqOtroDocG4Apue()).add(estadisticaElement.getLiqOtroDocG5Apue()).add(estadisticaElement.getLiqOtroDocRestoApue()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_DOC_TOTAL_RIFA.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_DOC_TOTAL_RIFA := (:ESTADISTICA.LIQ_OTRO_DOC_G1_RIFA+:ESTADISTICA.LIQ_OTRO_DOC_G2_RIFA+:ESTADISTICA.LIQ_OTRO_DOC_G3_RIFA+:ESTADISTICA.LIQ_OTRO_DOC_G4_RIFA+:ESTADISTICA.LIQ_OTRO_DOC_G5_RIFA+:ESTADISTICA.LIQ_OTRO_DOC_RESTO_RIFA);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_DOC_TOTAL_RIFA.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_DOC_TOTAL_RIFA")
	public void liqOtroDocTotalRifa_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroDocTotalRifa(((estadisticaElement.getLiqOtroDocG1Rifa().add(estadisticaElement.getLiqOtroDocG2Rifa()).add(estadisticaElement.getLiqOtroDocG3Rifa()).add(estadisticaElement.getLiqOtroDocG4Rifa()).add(estadisticaElement.getLiqOtroDocG5Rifa()).add(estadisticaElement.getLiqOtroDocRestoRifa()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_DOC_TOTAL_BOLE.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_DOC_TOTAL_BOLE := (:ESTADISTICA.LIQ_OTRO_DOC_G1_BOLE+:ESTADISTICA.LIQ_OTRO_DOC_G2_BOLE+:ESTADISTICA.LIQ_OTRO_DOC_G3_BOLE+:ESTADISTICA.LIQ_OTRO_DOC_G4_BOLE+:ESTADISTICA.LIQ_OTRO_DOC_G5_BOLE+:ESTADISTICA.LIQ_OTRO_DOC_RESTO_BOLE);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_DOC_TOTAL_BOLE.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_DOC_TOTAL_BOLE")
	public void liqOtroDocTotalBole_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroDocTotalBole(((estadisticaElement.getLiqOtroDocG1Bole().add(estadisticaElement.getLiqOtroDocG2Bole()).add(estadisticaElement.getLiqOtroDocG3Bole()).add(estadisticaElement.getLiqOtroDocG4Bole()).add(estadisticaElement.getLiqOtroDocG5Bole()).add(estadisticaElement.getLiqOtroDocRestoBole()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_DOC_TOTAL_TOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_DOC_TOTAL_TOTAL := (:ESTADISTICA.LIQ_OTRO_DOC_TOTAL_COMB+:ESTADISTICA.LIQ_OTRO_DOC_TOTAL_APUE+:ESTADISTICA.LIQ_OTRO_DOC_TOTAL_RIFA+:ESTADISTICA.LIQ_OTRO_DOC_TOTAL_BOLE);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_DOC_TOTAL_TOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_DOC_TOTAL_TOTAL")
	public void liqOtroDocTotalTotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroDocTotalTotal(((estadisticaElement.getLiqOtroDocTotalComb().add(estadisticaElement.getLiqOtroDocTotalApue()).add(estadisticaElement.getLiqOtroDocTotalRifa()).add(estadisticaElement.getLiqOtroDocTotalBole()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_T_G1_TOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_T_G1_TOTAL := (:ESTADISTICA.LIQ_OTRO_T_G1_COMB+:ESTADISTICA.LIQ_OTRO_T_G1_APUE+:ESTADISTICA.LIQ_OTRO_T_G1_RIFA+:ESTADISTICA.LIQ_OTRO_T_G1_BOLE);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_T_G1_TOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_T_G1_TOTAL")
	public void liqOtroTG1Total_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroTG1Total(((estadisticaElement.getLiqOtroTG1Comb().add(estadisticaElement.getLiqOtroTG1Apue()).add(estadisticaElement.getLiqOtroTG1Rifa()).add(estadisticaElement.getLiqOtroTG1Bole()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_T_G2_TOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_T_G2_TOTAL := (:ESTADISTICA.LIQ_OTRO_T_G2_COMB+:ESTADISTICA.LIQ_OTRO_T_G2_APUE+:ESTADISTICA.LIQ_OTRO_T_G2_RIFA+:ESTADISTICA.LIQ_OTRO_T_G2_BOLE);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_T_G2_TOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_T_G2_TOTAL")
	public void liqOtroTG2Total_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroTG2Total(((estadisticaElement.getLiqOtroTG2Comb().add(estadisticaElement.getLiqOtroTG2Apue()).add(estadisticaElement.getLiqOtroTG2Rifa()).add(estadisticaElement.getLiqOtroTG2Bole()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_T_G3_TOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_T_G3_TOTAL := (:ESTADISTICA.LIQ_OTRO_T_G3_COMB+:ESTADISTICA.LIQ_OTRO_T_G3_APUE+:ESTADISTICA.LIQ_OTRO_T_G3_RIFA+:ESTADISTICA.LIQ_OTRO_T_G3_BOLE);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_T_G3_TOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_T_G3_TOTAL")
	public void liqOtroTG3Total_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroTG3Total(((estadisticaElement.getLiqOtroTG3Comb().add(estadisticaElement.getLiqOtroTG3Apue()).add(estadisticaElement.getLiqOtroTG3Rifa()).add(estadisticaElement.getLiqOtroTG3Bole()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_T_G4_TOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_T_G4_TOTAL := (:ESTADISTICA.LIQ_OTRO_T_G4_COMB+:ESTADISTICA.LIQ_OTRO_T_G4_APUE+:ESTADISTICA.LIQ_OTRO_T_G4_RIFA+:ESTADISTICA.LIQ_OTRO_T_G4_BOLE);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_T_G4_TOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_T_G4_TOTAL")
	public void liqOtroTG4Total_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroTG4Total(((estadisticaElement.getLiqOtroTG4Comb().add(estadisticaElement.getLiqOtroTG4Apue()).add(estadisticaElement.getLiqOtroTG4Rifa()).add(estadisticaElement.getLiqOtroTG4Bole()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_T_G5_TOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_T_G5_TOTAL := (:ESTADISTICA.LIQ_OTRO_T_G5_COMB+:ESTADISTICA.LIQ_OTRO_T_G5_APUE+:ESTADISTICA.LIQ_OTRO_T_G5_RIFA+:ESTADISTICA.LIQ_OTRO_T_G5_BOLE);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_T_G5_TOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_T_G5_TOTAL")
	public void liqOtroTG5Total_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroTG5Total(((estadisticaElement.getLiqOtroTG5Comb().add(estadisticaElement.getLiqOtroTG5Apue()).add(estadisticaElement.getLiqOtroTG5Rifa()).add(estadisticaElement.getLiqOtroTG5Bole()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_T_G6_TOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_T_G6_TOTAL := (:ESTADISTICA.LIQ_OTRO_T_G6_COMB+:ESTADISTICA.LIQ_OTRO_T_G6_APUE+:ESTADISTICA.LIQ_OTRO_T_G6_RIFA+:ESTADISTICA.LIQ_OTRO_T_G6_BOLE);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_T_G6_TOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_T_G6_TOTAL")
	public void liqOtroTG6Total_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroTG6Total(((estadisticaElement.getLiqOtroTG6Comb().add(estadisticaElement.getLiqOtroTG6Apue()).add(estadisticaElement.getLiqOtroTG6Rifa()).add(estadisticaElement.getLiqOtroTG6Bole()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_T_RESTO_TOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_T_RESTO_TOTAL := (:ESTADISTICA.LIQ_OTRO_T_RESTO_COMB+:ESTADISTICA.LIQ_OTRO_T_RESTO_APUE+:ESTADISTICA.LIQ_OTRO_T_RESTO_RIFA+:ESTADISTICA.LIQ_OTRO_T_RESTO_BOLE);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_T_RESTO_TOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_T_RESTO_TOTAL")
	public void liqOtroTRestoTotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroTRestoTotal(((estadisticaElement.getLiqOtroTRestoComb().add(estadisticaElement.getLiqOtroTRestoApue()).add(estadisticaElement.getLiqOtroTRestoRifa()).add(estadisticaElement.getLiqOtroTRestoBole()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_T_TOTAL_COMB.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_T_TOTAL_COMB := (:ESTADISTICA.LIQ_OTRO_T_G1_COMB+:ESTADISTICA.LIQ_OTRO_T_G2_COMB+:ESTADISTICA.LIQ_OTRO_T_G3_COMB+:ESTADISTICA.LIQ_OTRO_T_G4_COMB+:ESTADISTICA.LIQ_OTRO_T_G5_COMB+:ESTADISTICA.LIQ_OTRO_T_RESTO_COMB);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_T_TOTAL_COMB.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_T_TOTAL_COMB")
	public void liqOtroTTotalComb_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroTTotalComb(((estadisticaElement.getLiqOtroTG1Comb().add(estadisticaElement.getLiqOtroTG2Comb()).add(estadisticaElement.getLiqOtroTG3Comb()).add(estadisticaElement.getLiqOtroTG4Comb()).add(estadisticaElement.getLiqOtroTG5Comb()).add(estadisticaElement.getLiqOtroTRestoComb()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_T_TOTAL_APUE.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_T_TOTAL_APUE := (:ESTADISTICA.LIQ_OTRO_T_G1_APUE+:ESTADISTICA.LIQ_OTRO_T_G2_APUE+:ESTADISTICA.LIQ_OTRO_T_G3_APUE+:ESTADISTICA.LIQ_OTRO_T_G4_APUE+:ESTADISTICA.LIQ_OTRO_T_G5_APUE+:ESTADISTICA.LIQ_OTRO_T_RESTO_APUE);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_T_TOTAL_APUE.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_T_TOTAL_APUE")
	public void liqOtroTTotalApue_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroTTotalApue(((estadisticaElement.getLiqOtroTG1Apue().add(estadisticaElement.getLiqOtroTG2Apue()).add(estadisticaElement.getLiqOtroTG3Apue()).add(estadisticaElement.getLiqOtroTG4Apue()).add(estadisticaElement.getLiqOtroTG5Apue()).add(estadisticaElement.getLiqOtroTRestoApue()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_T_TOTAL_RIFA.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_T_TOTAL_RIFA := (:ESTADISTICA.LIQ_OTRO_T_G1_RIFA+:ESTADISTICA.LIQ_OTRO_T_G2_RIFA+:ESTADISTICA.LIQ_OTRO_T_G3_RIFA+:ESTADISTICA.LIQ_OTRO_T_G4_RIFA+:ESTADISTICA.LIQ_OTRO_T_G5_RIFA+:ESTADISTICA.LIQ_OTRO_T_RESTO_RIFA);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_T_TOTAL_RIFA.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_T_TOTAL_RIFA")
	public void liqOtroTTotalRifa_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroTTotalRifa(((estadisticaElement.getLiqOtroTG1Rifa().add(estadisticaElement.getLiqOtroTG2Rifa()).add(estadisticaElement.getLiqOtroTG3Rifa()).add(estadisticaElement.getLiqOtroTG4Rifa()).add(estadisticaElement.getLiqOtroTG5Rifa()).add(estadisticaElement.getLiqOtroTRestoRifa()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_T_TOTAL_BOLE.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_T_TOTAL_BOLE := (:ESTADISTICA.LIQ_OTRO_T_G1_BOLE+:ESTADISTICA.LIQ_OTRO_T_G2_BOLE+:ESTADISTICA.LIQ_OTRO_T_G3_BOLE+:ESTADISTICA.LIQ_OTRO_T_G4_BOLE+:ESTADISTICA.LIQ_OTRO_T_G5_BOLE+:ESTADISTICA.LIQ_OTRO_T_RESTO_BOLE);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_T_TOTAL_BOLE.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_T_TOTAL_BOLE")
	public void liqOtroTTotalBole_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroTTotalBole(((estadisticaElement.getLiqOtroTG1Bole().add(estadisticaElement.getLiqOtroTG2Bole()).add(estadisticaElement.getLiqOtroTG3Bole()).add(estadisticaElement.getLiqOtroTG4Bole()).add(estadisticaElement.getLiqOtroTG5Bole()).add(estadisticaElement.getLiqOtroTRestoBole()))));
	}

	/* Original PL/SQL code code for TRIGGER LIQ_OTRO_T_TOTAL_TOTAL.FORMULA-CALCULATION
	begin
	:ESTADISTICA.LIQ_OTRO_T_TOTAL_TOTAL := (:ESTADISTICA.LIQ_OTRO_T_TOTAL_COMB+:ESTADISTICA.LIQ_OTRO_T_TOTAL_APUE+:ESTADISTICA.LIQ_OTRO_T_TOTAL_RIFA+:ESTADISTICA.LIQ_OTRO_T_TOTAL_BOLE);
	end;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * LIQ_OTRO_T_TOTAL_TOTAL.FORMULA-CALCULATION
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "FORMULA-CALCULATION", item = "LIQ_OTRO_T_TOTAL_TOTAL")
	public void liqOtroTTotalTotal_FormulaCalculation() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		estadisticaElement.setLiqOtroTTotalTotal(((estadisticaElement.getLiqOtroTTotalComb().add(estadisticaElement.getLiqOtroTTotalApue()).add(estadisticaElement.getLiqOtroTTotalRifa()).add(estadisticaElement.getLiqOtroTTotalBole()))));
	}

	/* Original PL/SQL code code for TRIGGER RB_LIQ_OTRO_CONCEPTO.WHEN-RADIO-CHANGED
	 VISUALIZACION_LIQ_OTROS;
	*/
	/*
	 *<p>
	 *<b>Migration Comments</b>
	 * Generated from trigger:
	 * RB_LIQ_OTRO_CONCEPTO.WHEN-RADIO-CHANGED
	 *
	 *
	 *</p>
	*/

	@ActionTrigger(action = "WHEN-RADIO-CHANGED", item = "RB_LIQ_OTRO_CONCEPTO")
	public void rbLiqOtroConcepto_radioGroupChange() {

		//F2J_WARNING : Caution, the variable may be null.
		EstadisticaAdapter estadisticaElement = (EstadisticaAdapter) this.getFormModel().getEstadistica().getRowAdapter(true);

		this.getTask().getServices().visualizacionLiqOtros(estadisticaElement);
	}

}
