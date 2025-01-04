import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import { Button, Modal, ModalBody, ModalFooter, ModalHeader } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';
import { deleteEntity, getEntity } from './competition-payment-from-sponsor.reducer';

export const CompetitionPaymentFromSponsorDeleteDialog = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();
  const { id } = useParams<'id'>();

  const [loadModal, setLoadModal] = useState(false);

  useEffect(() => {
    dispatch(getEntity(id));
    setLoadModal(true);
  }, []);

  const competitionPaymentFromSponsorEntity = useAppSelector(state => state.competitionPaymentFromSponsor.entity);
  const updateSuccess = useAppSelector(state => state.competitionPaymentFromSponsor.updateSuccess);

  const handleClose = () => {
    navigate('/competition-payment-from-sponsor');
  };

  useEffect(() => {
    if (updateSuccess && loadModal) {
      handleClose();
      setLoadModal(false);
    }
  }, [updateSuccess]);

  const confirmDelete = () => {
    dispatch(deleteEntity(competitionPaymentFromSponsorEntity.id));
  };

  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose} data-cy="competitionPaymentFromSponsorDeleteDialogHeading">
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="affliateMarketplaceApp.competitionPaymentFromSponsor.delete.question">
        <Translate
          contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.delete.question"
          interpolate={{ id: competitionPaymentFromSponsorEntity.id }}
        >
          Are you sure you want to delete this CompetitionPaymentFromSponsor?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button
          id="jhi-confirm-delete-competitionPaymentFromSponsor"
          data-cy="entityConfirmDeleteButton"
          color="danger"
          onClick={confirmDelete}
        >
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

export default CompetitionPaymentFromSponsorDeleteDialog;
